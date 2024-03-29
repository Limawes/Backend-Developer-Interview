package br.com.mobiauto.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosRequest {
  private Long idUsuario;
  private String nome;
  private String email;
  private String senha;
  private Long cargo;
  private Long lojaId;
}
