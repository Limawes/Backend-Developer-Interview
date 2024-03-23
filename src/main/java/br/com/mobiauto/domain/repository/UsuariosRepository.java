package br.com.mobiauto.domain.repository;

import br.com.mobiauto.domain.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuarioModel, Long> {

  @Override
  List<UsuarioModel> findAll();

  @Override
  Optional<UsuarioModel> findById(Long id);
}
