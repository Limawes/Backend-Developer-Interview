package br.com.mobiauto.domain.repository;

import br.com.mobiauto.domain.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientesRepository extends JpaRepository<ClienteModel, Long> {

  @Override
  Optional<ClienteModel> findById(Long id);

  @Override
  List<ClienteModel> findAll();
}
