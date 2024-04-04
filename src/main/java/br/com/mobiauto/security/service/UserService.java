package br.com.mobiauto.security.service;

import br.com.mobiauto.domain.model.RevendaModel;
import br.com.mobiauto.domain.repository.RevendaRepository;
import br.com.mobiauto.security.domain.model.ERole;
import br.com.mobiauto.security.domain.model.SystemRolesModel;
import br.com.mobiauto.security.domain.model.UserModel;
import br.com.mobiauto.security.domain.repository.SystemRoleRepository;
import br.com.mobiauto.security.domain.repository.UserRepository;
import br.com.mobiauto.security.domain.request.UserRequest;
import br.com.mobiauto.security.domain.response.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RevendaRepository revendaRepository;

    private final SystemRoleRepository systemRoleRepository;

    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, RevendaRepository revendaRepository, SystemRoleRepository systemRoleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
      this.revendaRepository = revendaRepository;
      this.systemRoleRepository = systemRoleRepository;
        this.encoder = encoder;
    }

    @Transactional
    public void save(final UserRequest userRequest, final Long user_id) {
        validateInputs(userRequest);

        UserModel userModel = new UserModel();

        if (user_id != null) {
            Optional<UserModel> actualUser = userRepository.findById(user_id);
            if (actualUser.isEmpty()) {
                throw new RuntimeException("Usuário não encontrado");
            }
            if (!actualUser.get().getUsername().equals(userRequest.getUsername())) {
                checkUsernameExistsInDatabase(userRequest.getUsername());
            }
            if (!actualUser.get().getEmail().equals(userRequest.getEmail())) {
                checkEmailExistsInDatabase(userRequest.getEmail());
            }
            userModel.setId(user_id);
        } else {
            checkUsernameExistsInDatabase(userRequest.getUsername());
            checkEmailExistsInDatabase(userRequest.getEmail());
        }

        Set<String> strRoles = userRequest.getRole();
        Set<SystemRolesModel> roles = new HashSet<>();

        strRoles.forEach(role -> {
            Optional<ERole> eRole = ERole.of(role);
            if (eRole.isEmpty()) {
                throw new RuntimeException("Permissão não encontrada: " + eRole);
            }
            Optional<SystemRolesModel> rolesModel = systemRoleRepository.findByName(eRole.get().name());
            if (rolesModel.isEmpty()) {
                throw new RuntimeException("Permissão não encontrada: " + eRole);
            }
            roles.add(rolesModel.get());
        });

        userModel.setUsername(userRequest.getUsername());
        userModel.setPassword(encoder.encode(userRequest.getPassword()));
        userModel.setEmail(userRequest.getEmail());
        if(userRequest.getIdRevenda() != null){
            RevendaModel revenda = revendaRepository.findById(userRequest.getIdRevenda())
              .orElseThrow(() -> new IllegalArgumentException("Revenda não encontrada"));
            userModel.setLoja(revenda);
        }
        userModel.setRoles(roles);

        userRepository.save(userModel);
    }

    private void validateInputs(final UserRequest userRequest) {
        if (userRequest.getEmail().isEmpty()) {
            throw new RuntimeException("O email não foi informado!");
        }
        if (!isValidEmail(userRequest.getEmail())) {
            throw new RuntimeException("Email informado inválido!");
        }
        if (userRequest.getUsername().isEmpty()) {
            throw new RuntimeException("O usuário não foi informado!");
        }
        if (userRequest.getPassword().isEmpty()) {
            throw new RuntimeException("Por favor informe a senha!");
        }
        if (userRequest.getRole() == null || userRequest.getRole().isEmpty()) {
            throw new RuntimeException("");
        }
    }

    public static boolean isValidEmail(String email) {
        // utilizando um regex pra verificar o email, se é válido ou não
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public UserModel findById(final Long id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        if (userModel.isEmpty()) {
            throw new RuntimeException("");
        }

        return userModel.get();
    }

    public List<UserResponse> findAll() {
        List<UserResponse> userResponseList = new ArrayList<>();
        List<UserModel> users = userRepository.findAll();

        if (users.isEmpty()) {
            return userResponseList;
        }
        for(UserModel user : users) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setEmail(user.getEmail());
            userResponse.setUsername(user.getUsername());
            userResponse.setRoles(user.getRoles());
            userResponse.setRevenda(user.getLoja().getNomeSocial());

            userResponseList.add(userResponse);
        }

        return userResponseList;
    }

    private void checkUsernameExistsInDatabase(final String username) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Usuário (username) já cadastrado");
        }
    }

    private void checkEmailExistsInDatabase(final String email) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("E-mail já cadastrado");
        }
    }

}
