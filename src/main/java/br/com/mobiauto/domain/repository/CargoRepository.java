package br.com.mobiauto.domain.repository;

import br.com.mobiauto.domain.model.CargoModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<CargoModel, Long> {

  @Override
  <S extends CargoModel> List<S> findAll(Example<S> example);

  @Override
  Optional<CargoModel> findById(Long aLong);
}
