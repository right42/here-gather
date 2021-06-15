package me.right42.heregather.domain.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.interest.group.GroupInterest;

public interface GroupInterestRepository extends JpaRepository<GroupInterest, Long> {
}
