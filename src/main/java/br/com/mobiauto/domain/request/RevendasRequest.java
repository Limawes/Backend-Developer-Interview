package br.com.mobiauto.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RevendasRequest {
  private Long idRevenda;
  private String codigoIdentificador;
  private String cnpj;
  private String nomeSocial;
}
