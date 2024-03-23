package br.com.mobiauto.domain.repository;

import br.com.mobiauto.domain.model.RevendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RevendaRepository extends JpaRepository<RevendaModel, Long> {

  @Override
  Optional<RevendaModel> findById(Long revenda_id);

  @Override
  List<RevendaModel> findAll();

  Boolean existsByCnpj(String cnpj);
  Boolean existsByCodigoIdentificador(String codigo);
}
