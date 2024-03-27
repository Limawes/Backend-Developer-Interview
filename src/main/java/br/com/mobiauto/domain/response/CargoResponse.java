package br.com.mobiauto.domain.response;

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
public class CargoResponse {
  private Long idCargo;
  private String nome;
  private UsuarioModel usuario;
  private PermissoesModel permissoes;
}
