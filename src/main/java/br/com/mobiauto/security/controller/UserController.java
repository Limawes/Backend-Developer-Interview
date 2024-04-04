package br.com.mobiauto.security.controller;

import br.com.mobiauto.security.domain.model.UserModel;
import br.com.mobiauto.security.domain.request.UserRequest;
import br.com.mobiauto.security.domain.response.UserResponse;
import br.com.mobiauto.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@RequestBody UserRequest userRequest) {
        log.info("Creating user with data: {}", userRequest.toString());
        userService.save(userRequest, null);
        return ResponseEntity.created(null).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity update(@RequestBody UserRequest userRequest, @PathVariable Long id) {
        log.info("Updating user with data: {}", userRequest.toString());
        userService.save(userRequest, id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> findAll() {
        log.info("Geting all users");
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findById(@PathVariable Long id) {
        log.info("Searching user with ID: ", id);
        UserModel userModel = userService.findById(id);
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(userModel.getEmail());
        userResponse.setUsername(userModel.getUsername());
        userResponse.setRoles(userModel.getRoles());
        return userResponse;
    }

}
