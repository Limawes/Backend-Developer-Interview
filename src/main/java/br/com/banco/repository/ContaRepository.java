package br.com.banco.repository;

import br.com.banco.model.ContaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<ContaModel, Long> {

  @Query(value = "from ContaModel where id_conta = :contaId")
  List<ContaModel> findByContaId(@Param("contaId") Long contaId);
}
