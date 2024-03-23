package br.com.mobiauto.domain.request;

import br.com.mobiauto.domain.model.UsuariosModel;
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
  private Long id;
  private String nome;
  private UsuariosModel usuario;
  private PermissoesModel permissoes;
}
