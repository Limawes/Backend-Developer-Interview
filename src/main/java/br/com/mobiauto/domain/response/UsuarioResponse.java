package br.com.mobiauto.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
  private Long idUsuario;
  private String nome;
  private String email;
  private CargoResponse cargo;
  private RevendaResponse lojaId;
}
