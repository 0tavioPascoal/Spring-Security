package dev.tavin.security.infra.repository;

import dev.tavin.security.infra.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserDetails> findUserByEmail(String email);

    Optional<UserModel> findByEmail(String email);
}
