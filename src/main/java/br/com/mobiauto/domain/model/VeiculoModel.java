package br.com.mobiauto.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TBL_VEICULOS")
public class VeiculoModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_veiculo", unique = true, nullable = false)
  private Long idVeiculo;

  @Column(name = "marca")
  private String marca;

  @Column(name = "modelo")
  private String modelo;

  @Column(name = "versao")
  private String versao;

  @Column(name = "ano_modelo")
  private String anoModelo;
}
