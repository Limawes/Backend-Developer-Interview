package br.com.mobiauto.domain.repository;

import br.com.mobiauto.domain.model.OportunidadesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OportunidadesRepository extends JpaRepository<OportunidadesModel, Long> {

  @Override
  Optional<OportunidadesModel> findById(Long id);

  @Override
  List<OportunidadesModel> findAll();

  //Buscar oportunidades de uma loja específica
  @Query("select * from Oportunidades where loja_id = :id_loja")
  List<OportunidadesModel> findByLojaId(Long id_loja);


}
