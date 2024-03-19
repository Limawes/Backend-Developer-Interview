package br.com.banco.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class RevendasModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "codigo_identificador", unique = true, nullable = false)
  private String codigoIdentificador;

  @Column(name = "cnpj", unique = true, nullable = false)
  private String cnpj;

  @Column(name = "nome_social", nullable = false)
  private String nomeSocial;

}
