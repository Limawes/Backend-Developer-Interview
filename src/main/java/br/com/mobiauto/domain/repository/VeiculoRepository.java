package br.com.mobiauto.domain.repository;

import br.com.mobiauto.domain.model.VeiculosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculosModel, Long> {

  @Override
  List<VeiculosModel> findAll();

  @Override
  Optional<VeiculosModel> findById(Long aLong);
}
