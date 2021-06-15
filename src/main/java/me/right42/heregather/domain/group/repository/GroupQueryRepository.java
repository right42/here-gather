package me.right42.heregather.domain.group.repository;

import static me.right42.heregather.domain.group.QGroup.*;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.group.Group;
import me.right42.heregather.web.dto.group.GroupSearchDto;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupQueryRepository {

	private final JPAQueryFactory query;

	public List<Group> findAll(GroupSearchDto groupSearchDto) {
		return query
			.selectFrom(group)
			.where(
				nameContains(groupSearchDto.getKeyword())
			)
			.fetch();
	}

	private BooleanExpression nameContains(String groupName) {
		if(StringUtils.hasText(groupName)) {
			return group.name.contains(groupName);
		}
		return null;
	}
}
