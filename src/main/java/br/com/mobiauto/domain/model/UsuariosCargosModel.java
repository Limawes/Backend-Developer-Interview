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
  @JoinColumn(name = "usuario_id")
  private UsuarioModel usuarioId;

  @ManyToOne
  @JoinColumn(name = "cargo_id")
  private CargoModel cargoId;

}
