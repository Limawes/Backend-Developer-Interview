package br.com.mobiauto.domain.model;

import br.com.mobiauto.security.model.PermissoesModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Cargos")
public class CargoModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_cargo", nullable = false)
  private Long idCargo;

  @Column(name = "nome")
  private String nome;

  @ManyToMany(mappedBy = "cargo")
  private List<UsuarioModel> usuarios;


  @ManyToMany
  @JoinTable(
    name = "cargos_permissao",
    joinColumns = @JoinColumn(name = "cargo_id", referencedColumnName = "id_cargo"),
    inverseJoinColumns = @JoinColumn(name = "permissao_id", referencedColumnName = "id_permissao")
  )
  private List<PermissoesModel> permissoes;

}
