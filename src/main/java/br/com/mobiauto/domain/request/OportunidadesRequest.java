package br.com.mobiauto.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class OportunidadesRequest {
  private Long idOportunidade;
  private String codigoIdentificador;
  private String status;
  private String motivoConclusao;
  private LocalDateTime dataAtribuicao;
  private LocalDateTime dataConclusao;
  private Long clienteId;
  private Long veiculoId;
  private Long lojaId;
  private Long responsavelId;
}
