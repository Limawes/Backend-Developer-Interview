package br.com.mobiauto.domain.request;

import br.com.mobiauto.domain.model.UsuarioModel;
import br.com.mobiauto.security.model.PermissoesModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoRequest {
  private Long idCargo;
  private String nome;
  private Long usuario;
  private Long permissoes;
}
