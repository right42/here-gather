package me.right42.heregather.domain.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.group.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
