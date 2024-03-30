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

  //Buscar oportunidades de uma loja específica
  @Query(value = "select * from Oportunidades where loja_id = :id_loja", nativeQuery = true)
  List<OportunidadeModel> findByLojaId(Long id_loja);

  @Query(value = "select count(*) from Oportunidades where id_responsavel = :id_responsavel", nativeQuery = true)
  List<OportunidadeModel> countOportunidadeByResponsavel(Long id_responsavel);

  @Query(value = "select * from Oportunidades where id_responsavel is null", nativeQuery = true)
  List<OportunidadeModel> oportunidadeSemResponsavel();

}
