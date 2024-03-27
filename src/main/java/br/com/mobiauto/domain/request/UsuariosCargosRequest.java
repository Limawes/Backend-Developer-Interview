package br.com.mobiauto.domain.request;

import br.com.mobiauto.domain.model.CargoModel;
import br.com.mobiauto.domain.model.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosCargosRequest {
  private Long usuarioId;
  private Long cargoId;
}
