package br.com.mobiauto.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Usuarios")
public class UsuariosModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private UUID id;

  @Column(name = "codigo_identificador", nullable = false)
  private String codigoIdentificador;

  @Column(name = "nome", nullable = false)
  private String nome;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "senha")
  private String senha;

  @Column(name = "cargo")
  private String cargo;

  @ManyToOne
  @JoinColumn(name = "loja_id_id")
  private RevendasModel lojaId;

}
