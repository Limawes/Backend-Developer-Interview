package br.com.mobiauto.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientesResponse {

  private Long idCliente;
  private String nome;
  private String email;
  private String telefone;
}
