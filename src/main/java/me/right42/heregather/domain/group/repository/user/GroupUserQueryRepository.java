package me.right42.heregather.domain.group.repository.user;

import static me.right42.heregather.domain.group.QGroupUser.*;
import static me.right42.heregather.domain.role.QGroupUserRole.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.group.GroupUser;
import me.right42.heregather.domain.group.type.JoinStatus;
import me.right42.heregather.domain.role.GroupUserRole;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupUserQueryRepository {

	private final JPAQueryFactory query;

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

	public GroupUserRole findRoleByGroupUser(GroupUser groupUserParam) {
		return query
				.selectFrom(groupUserRole)
				.join(groupUser)
					.on(groupUserRole.groupUser.eq(groupUser))
				.where(groupUser.eq(groupUserParam))
				.fetchOne();
	}

}
