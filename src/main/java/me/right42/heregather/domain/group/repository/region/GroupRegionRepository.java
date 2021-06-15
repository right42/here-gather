package me.right42.heregather.domain.group.repository.region;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.group.Group;
import me.right42.heregather.domain.region.GroupRegion;

public interface GroupRegionRepository extends JpaRepository<GroupRegion, Long> {

	List<GroupRegion> findAllByGroup(Group group);
}
