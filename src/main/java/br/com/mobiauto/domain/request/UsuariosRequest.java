package br.com.mobiauto.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UsuariosRequest {
  private Long id;
  private String codigoIdentificador;
  private String nome;
  private String email;
  private String senha;
  private CargoRequest cargo;
  private RevendasRequest lojaId;
}
