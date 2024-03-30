package br.com.mobiauto.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "usuarios_cargos")
public class UsuariosCargosModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuarios_cargos", nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "id_usuario")
  private UsuarioModel usuarioId;

  @ManyToOne
  @JoinColumn(name = "id_cargo")
  private CargoModel cargoId;

}
