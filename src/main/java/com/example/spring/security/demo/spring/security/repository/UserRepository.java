package com.example.spring.security.demo.spring.security.repository;

import com.example.spring.security.demo.spring.security.entity.Role;
import com.example.spring.security.demo.spring.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
