package br.com.mobiauto.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class OportunidadesRequest {
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
