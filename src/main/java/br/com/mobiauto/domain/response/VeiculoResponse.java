package br.com.mobiauto.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoResponse {
  private Long idVeiculo;
  private String marca;
  private String modelo;
  private String versao;
  private String anoModelo;
}
