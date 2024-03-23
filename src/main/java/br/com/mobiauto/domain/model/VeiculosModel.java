package br.com.mobiauto.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Veiculos")
public class VeiculosModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(name = "marca")
  private String marca;

  @Column(name = "modelo")
  private String modelo;

  @Column(name = "versao")
  private String versao;

  @Column(name = "ano_modelo")
  private String anoModelo;
}
