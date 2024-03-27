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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario", nullable = false)
  private Long idUsuario;

  @Column(name = "nome", nullable = false)
  private String nome;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "senha")
  private String senha;


  @ManyToOne
  @JoinTable(
    name = "usuarios_cargos",
    joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario"),
    inverseJoinColumns = @JoinColumn(name = "cargo_id", referencedColumnName = "id_cargo")
  )
  private CargoModel cargo;

  @ManyToOne
  @JoinColumn(name = "loja_id")
  private RevendaModel lojaId;

}
