package br.com.mobiauto.domain.repository;

import br.com.mobiauto.domain.model.UsuarioModel;
import br.com.mobiauto.domain.model.UsuariosCargosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuariosCargosRepository extends JpaRepository<UsuariosCargosModel, Long> {

  @Override
  List<UsuariosCargosModel> findAll();

  @Query(value = "select * from usuarios_cargos where usuario_id = :usuarioId", nativeQuery = true)
  Optional<UsuariosCargosModel> findByUsuarioId(Long usuarioId);
}
