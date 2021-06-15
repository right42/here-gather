package me.right42.heregather.domain.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.role.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
