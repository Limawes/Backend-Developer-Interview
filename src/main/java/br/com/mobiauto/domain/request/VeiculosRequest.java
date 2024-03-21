package br.com.mobiauto.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class VeiculosRequest {
  private String marca;
  private String modelo;
  private String versao;
  private String anoModelo;
}
