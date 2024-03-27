package br.com.mobiauto.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ClientesRequest {
  private Long idCliente;
  private String nome;
  private String email;
  private String telefone;
}
