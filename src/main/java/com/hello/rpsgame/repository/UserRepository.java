package com.hello.rpsgame.repository;

import com.hello.rpsgame.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByAlias(final String alias);
}
