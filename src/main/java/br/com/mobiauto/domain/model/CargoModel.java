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
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "nome")
  private String nome;

  @ManyToMany(mappedBy = "cargo")
  private Collection<UsuariosModel> usuario;

  @ManyToMany
  @JoinTable(
    name = "cargos_permissao",
    joinColumns = @JoinColumn(name = "cargo_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "permissao_id", referencedColumnName = "id")
  )
  private List<PermissoesModel> permissoes;

}
