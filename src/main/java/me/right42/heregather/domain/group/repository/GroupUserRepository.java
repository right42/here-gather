package me.right42.heregather.domain.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.group.GroupUser;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {
}
