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


}
