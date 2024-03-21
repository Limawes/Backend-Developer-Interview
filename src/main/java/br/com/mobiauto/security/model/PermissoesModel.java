package br.com.mobiauto.security.model;

import br.com.mobiauto.domain.model.CargoModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Permissao")
public class PermissoesModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "nome")
  private String nome;

  @ManyToMany(mappedBy = "permissoes")
  private Collection<CargoModel> cargo;

}
