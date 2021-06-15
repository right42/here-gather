package me.right42.heregather.domain.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.role.GroupUserRole;

public interface GroupUserRoleRepository extends JpaRepository<GroupUserRole, Long> {
}
