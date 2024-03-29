package br.com.mobiauto.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Oportunidades")
public class OportunidadeModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_oportunidade", unique = true, nullable = false)
  private Long idOportunidade;

  @Column(name = "status")
  private String status;

  @Column(name = "motivo_conclusao")
  private String motivoConclusao;

  @Column(name = "data_atribuicao")
  private LocalDateTime dataAtribuicao;

  @Column(name = "data_conclusao")
  private LocalDateTime dataConclusao;

  @ManyToOne
  @JoinColumn(name = "id_cliente")
  private ClienteModel cliente;

  @ManyToOne
  @JoinColumn(name = "id_veiculo")
  private VeiculoModel veiculo;

  @ManyToOne
  @JoinColumn(name = "id_revenda")
  private RevendaModel loja;

  @OneToOne
  @JoinColumn(name = "id_responsavel")
  private UsuarioModel responsavelId;
}
