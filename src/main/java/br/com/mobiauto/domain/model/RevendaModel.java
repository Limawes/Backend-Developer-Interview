package br.com.mobiauto.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "TBL_REVENDA")
public class RevendaModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_revenda", updatable = false, nullable = false)
  private Long idRevenda;

  @Column(name = "cnpj", unique = true, nullable = false)
  private String cnpj;

  @Column(name = "nome_social", nullable = false)
  private String nomeSocial;

}
