package br.com.mobiauto.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TBL_CLIENTES")
public class ClienteModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_cliente", unique = true, nullable = false)
  private Long idCliente;

  @Column(name = "nome")
  private String nome;

  @Column(name = "email")
  private String email;

  @Column(name = "telefone")
  private String telefone;

}
