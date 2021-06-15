package me.right42.heregather.domain.group.repository.interest;

import static me.right42.heregather.domain.group.QGroup.*;
import static me.right42.heregather.domain.interest.QInterest.*;
import static me.right42.heregather.domain.interest.group.QGroupInterest.*;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.group.Group;
import me.right42.heregather.web.dto.interst.InterestResponseDto;

@Repository
@RequiredArgsConstructor
public class GroupInterestQueryRepository {

	private final JPAQueryFactory query;

	public List<InterestResponseDto> findAllByGroupWithInterest(Group paramGroup){

		return query
			.select(
				Projections.constructor(InterestResponseDto.class,
					interest.id,
					interest.name
				)
			)
			.from(groupInterest)
			.join(group)
				.on(group.eq(groupInterest.group))
			.join(interest)
				.on(interest.eq(groupInterest.interest))
			.fetch();
	}

	private BooleanExpression groupNameContains(String groupName) {
		if(StringUtils.hasText(groupName)) {
			return group.name.contains(groupName);
		}
		return null;
	}

}
