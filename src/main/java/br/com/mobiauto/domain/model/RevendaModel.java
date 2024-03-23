package br.com.mobiauto.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Revendas")
public class RevendaModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Column(name = "codigo_identificador", unique = true, nullable = false)
  private String codigoIdentificador;

  @Column(name = "cnpj", unique = true, nullable = false)
  private String cnpj;

  @Column(name = "nome_social", nullable = false)
  private String nomeSocial;

}
