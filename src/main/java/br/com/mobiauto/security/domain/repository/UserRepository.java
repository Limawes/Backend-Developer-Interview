package br.com.mobiauto.security.domain.repository;

import br.com.mobiauto.security.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Override
    List<UserModel> findAll();

    @Override
    Optional<UserModel> findById(Long id);
}
