package br.com.mobiauto.domain.repository;

import br.com.mobiauto.domain.model.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosModel, Long> {

  @Override
  List<UsuariosModel> findAll();

  @Override
  Optional<UsuariosModel> findById(Long id);
}
