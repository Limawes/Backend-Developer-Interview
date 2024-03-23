package br.com.mobiauto.domain.response;

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
  private String codigoIdentificador;
  private String status;
  private String motivoConclusao;
  private LocalDateTime dataAtribuicao;
  private LocalDateTime dataConclusao;
  private ClientesRequest clienteId;
  private VeiculosRequest veiculoId;
  private RevendasRequest lojaId;
  private UsuariosRequest responsavelId;
}
