package br.com.mobiauto.domain.response;

import br.com.mobiauto.domain.model.CargoModel;
import br.com.mobiauto.domain.model.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosCargosResponse {
  private Long id;
  private UsuarioModel usuario;
  private CargoModel cargo;
}
