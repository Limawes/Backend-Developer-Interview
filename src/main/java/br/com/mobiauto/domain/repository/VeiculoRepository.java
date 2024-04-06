package br.com.mobiauto.domain.repository;

import br.com.mobiauto.domain.model.VeiculoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoModel, Long> {

  @Override
  List<VeiculoModel> findAll();

  @Override
  Optional<VeiculoModel> findById(Long id);
}
