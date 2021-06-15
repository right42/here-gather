package me.right42.heregather.domain.group.repository.user;

import static me.right42.heregather.domain.group.QGroupUser.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.group.type.JoinStatus;

@Repository
@RequiredArgsConstructor
public class GroupUserQueryRepository {

	private final JPAQueryFactory query;

	@Transactional(readOnly = true)
	public Long countByGroupIdAndJoinStatus(Long groupId, JoinStatus joinStatus) {
		return query
			.select(
				groupUser.count()
			)
			.from(groupUser)
			.where(
				groupUser.group.id.eq(groupId),
				groupUser.joinStatus.eq(joinStatus)
			)
			.fetchOne()
			;
	}

}
