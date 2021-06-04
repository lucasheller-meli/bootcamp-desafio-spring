package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desafio.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

}
