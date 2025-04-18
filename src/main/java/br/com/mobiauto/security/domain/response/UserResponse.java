package br.com.mobiauto.security.domain.response;

import br.com.mobiauto.security.domain.model.SystemRolesModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Set<SystemRolesModel> roles;
    private String revenda;
}