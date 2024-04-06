package br.com.mobiauto.domain.repository;

import br.com.mobiauto.domain.model.OportunidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OportunidadesRepository extends JpaRepository<OportunidadeModel, Long> {

  @Override
  Optional<OportunidadeModel> findById(Long id);

  @Override
  List<OportunidadeModel> findAll();

  @Query(value = "SELECT id_responsavel, COUNT(*) AS oportunidades_count " +
    "FROM tbl_oportunidades " +
    "GROUP BY id_responsavel " +
    "ORDER BY oportunidades_count ASC " +
    "LIMIT 1", nativeQuery = true)
  List<Object[]> findResponsavelComMenosOportunidades();



  @Query(value = "select count(*) from Oportunidades where id_responsavel = :idResponsavel", nativeQuery = true)
  Long countOportunidadeByResponsavel(Long idResponsavel);

  @Query(value = "select * from tbl_oportunidades where id_responsavel is null", nativeQuery = true)
  List<OportunidadeModel> oportunidadeSemResponsavel();

}
