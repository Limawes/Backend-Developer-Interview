package br.com.mobiauto.security.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class UserRequest {
    private Long id;
    private String username;
    private String email;
    private Set<String> role;
    private String password;
    private Long idRevenda;
}