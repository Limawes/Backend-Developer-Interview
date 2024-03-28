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
  @JoinColumn(name = "cliente_id_id")
  private ClienteModel clienteId;

  @ManyToOne
  @JoinColumn(name = "veiculo_id")
  private VeiculoModel veiculoId;

  @ManyToOne
  @JoinColumn(name = "loja_id")
  private RevendaModel lojaId;

  @OneToOne
  @JoinColumn(name = "responsavel_id")
  private UsuarioModel responsavelId;
}
