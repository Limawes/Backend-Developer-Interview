package br.com.mobiauto.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Usuarios")
public class UsuarioModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(name = "codigo_identificador", nullable = false)
  private String codigoIdentificador;

  @Column(name = "nome", nullable = false)
  private String nome;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "senha")
  private String senha;


  @ManyToMany
  @JoinTable(
    name = "usuarios_cargos",
    joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "cargo_id", referencedColumnName = "id")
  )
  private List<CargoModel> cargo;

  @ManyToOne
  @JoinColumn(name = "loja_id_id")
  private RevendaModel lojaId;

}
