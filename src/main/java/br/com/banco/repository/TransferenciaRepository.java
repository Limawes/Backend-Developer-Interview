package br.com.banco.repository;

import br.com.banco.model.ContaModel;
import br.com.banco.model.TransferenciaModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransferenciaRepository extends JpaRepository<TransferenciaModel, Long> {

    List<TransferenciaModel> findAll();

    @Query(value = "select * from transferencia where conta_id = :contaId and id = :id", nativeQuery = true)
    Optional<TransferenciaModel> findByIdTransferencias(Long contaId, Long id);

    @Query(value = "select * from transferencia where data_transferencia between :inicio and :fim " +
        " and conta_id = :contaId", nativeQuery = true)
    List<TransferenciaModel> findByDataTransferenciaBetween(Date inicio, Date fim, Long contaId);

    @Query(value = "select * from transferencia where nome_operador_transacao = :operador" +
        " and conta_id = :contaId", nativeQuery = true)
    List<TransferenciaModel> findByNomeOperadorTransacao(String operador, Long contaId);

    @Query(value = "select * from transferencia where conta_id = :id", nativeQuery = true)
    List<TransferenciaModel> findByIdConta(Long id);

    @Query(value = "select * from transferencia where" +
        " conta_id = :contaId and id = :id and nome_operador_transacao = :operador" +
        " and data_transferencia between :inicio and :fim", nativeQuery = true)
    List<TransferenciaModel> findByAllFilters(Long contaId, Long id, String operador, Date inicio, Date fim);
}
