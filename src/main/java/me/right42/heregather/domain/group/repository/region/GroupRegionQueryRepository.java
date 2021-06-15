package me.right42.heregather.domain.group.repository.region;

import static me.right42.heregather.domain.group.QGroup.*;
import static me.right42.heregather.domain.interest.QInterest.*;
import static me.right42.heregather.domain.interest.group.QGroupInterest.*;
import static me.right42.heregather.domain.region.QGroupRegion.*;
import static me.right42.heregather.domain.region.QRegion.*;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.group.Group;
import me.right42.heregather.web.dto.region.RegionResponseDto;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupRegionQueryRepository {

	private final JPAQueryFactory query;

	public List<RegionResponseDto> findAllByGroupWithRegion(Group paramGroup){
		return query
			.select(
				Projections.constructor(RegionResponseDto.class,
					region.level,
					region.name.as("regionName")
				)
			)
			.from(groupRegion)
			.join(group)
				.on(group.eq(groupRegion.group))
			.join(region)
				.on(region.eq(groupRegion.region))
			.fetch();
	}

	private BooleanExpression groupNameContains(String groupName) {
		if(StringUtils.hasText(groupName)) {
			return group.name.contains(groupName);
		}
		return null;
	}


}
