package br.com.mobiauto.domain.repository;

import br.com.mobiauto.domain.model.RevendasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RevendaRepository extends JpaRepository<RevendasModel, Long> {

  @Override
  Optional<RevendasModel> findById(Long revenda_id);

  @Override
  List<RevendasModel> findAll();
}
