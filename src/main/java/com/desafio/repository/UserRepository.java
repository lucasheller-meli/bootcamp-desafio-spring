package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desafio.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
