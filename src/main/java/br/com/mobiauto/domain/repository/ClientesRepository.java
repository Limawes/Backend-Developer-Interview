package br.com.mobiauto.domain.repository;

import br.com.mobiauto.domain.model.ClientesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientesRepository extends JpaRepository<ClientesModel, Long> {

  @Override
  Optional<ClientesModel> findById(Long id);

  @Override
  List<ClientesModel> findAll();
}
