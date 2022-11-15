package com.graduation.daily.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User_register, Long> {
    Optional<User_register> findByuserid(String userid);
}
