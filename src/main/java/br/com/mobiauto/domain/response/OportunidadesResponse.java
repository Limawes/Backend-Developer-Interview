package br.com.mobiauto.domain.response;

import br.com.mobiauto.domain.model.ClienteModel;
import br.com.mobiauto.domain.model.RevendaModel;
import br.com.mobiauto.domain.model.UsuarioModel;
import br.com.mobiauto.domain.model.VeiculoModel;
import br.com.mobiauto.domain.request.ClientesRequest;
import br.com.mobiauto.domain.request.RevendasRequest;
import br.com.mobiauto.domain.request.UsuariosRequest;
import br.com.mobiauto.domain.request.VeiculosRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OportunidadesResponse {
  private Long idOportunidade;
  private String status;
  private String motivoConclusao;
  private LocalDateTime dataAtribuicao;
  private LocalDateTime dataConclusao;
  private ClienteModel cliente;
  private VeiculoModel veiculo;
  private RevendaModel loja;
  private UsuarioModel resposavel;
}
