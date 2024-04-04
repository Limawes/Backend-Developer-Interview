package br.com.mobiauto.controller;

import br.com.mobiauto.domain.model.ClienteModel;
import br.com.mobiauto.domain.request.ClientesRequest;
import br.com.mobiauto.domain.response.ClientesResponse;
import br.com.mobiauto.service.ClientesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/cliente")
public class ClienteController {

  private final ClientesService clientesService;

  public ClienteController(ClientesService clientesService) {
    this.clientesService = clientesService;
  }

  @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity create(@RequestBody ClientesRequest clientesRequest){
    log.info("Criando cliente: {}", clientesRequest.toString());
    clientesService.save(clientesRequest, null);
    return ResponseEntity.created(null).build();
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ClienteModel findById(@PathVariable Long id){
    log.info("Buscando cliente por id: {}", id);
    return clientesService.findById(id);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ClientesResponse> findAll(){
    return clientesService.findAll();
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity update(@RequestBody ClientesRequest clientesRequest, @PathVariable Long id){
    log.info("Atualizando um cliente pelo Id: {}", clientesRequest.toString());
    clientesService.save(clientesRequest, id);
    return ResponseEntity.ok().build();
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteById(@PathVariable Long id){
    log.info("Deletando um cliente a partir do Id: {}", id);
    clientesService.deleteById(id);
  }
}
