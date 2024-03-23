package br.com.mobiauto.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RevendaResponse {
  private Long id;
  private String codigoIdentificador;
  private String cnpj;
  private String nomeSocial;
}
