package br.com.banco.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaResponse {
    private Long id;
    private Date dataTransferencia;
    private BigDecimal valor;
    private String tipo;
    private String nomeOperadorTransacao;
    private ContaResponse conta;
}
