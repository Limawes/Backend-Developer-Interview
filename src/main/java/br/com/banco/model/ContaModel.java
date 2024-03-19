package br.com.banco.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Conta")
public class ContaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long id_conta;

    @Column(name = "nome_responsavel")
    private String nome_responsavel;
}
