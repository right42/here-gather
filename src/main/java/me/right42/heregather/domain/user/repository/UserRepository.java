package me.right42.heregather.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
