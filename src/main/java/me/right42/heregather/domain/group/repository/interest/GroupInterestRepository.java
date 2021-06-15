package me.right42.heregather.domain.group.repository.interest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.group.Group;
import me.right42.heregather.domain.interest.group.GroupInterest;

public interface GroupInterestRepository extends JpaRepository<GroupInterest, Long> {
	List<GroupInterest> findAllByGroup(Group group);
}
