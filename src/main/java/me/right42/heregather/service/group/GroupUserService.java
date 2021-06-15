package me.right42.heregather.service.group;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.group.Group;
import me.right42.heregather.domain.group.GroupUser;
import me.right42.heregather.domain.group.repository.user.GroupUserQueryRepository;
import me.right42.heregather.domain.group.repository.user.GroupUserRepository;
import me.right42.heregather.domain.group.repository.user.GroupUserRoleRepository;
import me.right42.heregather.domain.group.type.JoinStatus;
import me.right42.heregather.domain.role.GroupUserRole;
import me.right42.heregather.domain.role.Role;
import me.right42.heregather.domain.role.type.RoleType;
import me.right42.heregather.domain.user.User;
import me.right42.heregather.service.role.RoleService;

@Service
@RequiredArgsConstructor
public class GroupUserService {

	private final GroupUserRoleRepository groupUserRoleRepository;

	private final GroupUserRepository groupUserRepository;

	private final GroupUserQueryRepository groupUserQueryRepository;

	private final RoleService roleService;

	@Transactional
	public void groupUserInit(User user, Group group) {
		GroupUser groupUser = GroupUser.builder()
			.group(group)
			.user(user)
			.joinStatus(JoinStatus.JOIN)
			.build();

		GroupUser savedGroupUser = groupUserRepository.save(groupUser);

		setRole(savedGroupUser, RoleType.MANAGER);
	}

	@Transactional
	public void join(Group group, User user) {
		GroupUser groupUser = GroupUser.builder()
			.group(group)
			.user(user)
			.joinStatus(JoinStatus.PENDING)
			.build();

		GroupUser savedGroupUser = groupUserRepository.save(groupUser);

		setRole(savedGroupUser, RoleType.NORMAL);
	}

	@Transactional(readOnly = true)
	public Long getJoinedUserCount(Long groupId) {
		return groupUserQueryRepository.countByGroupIdAndJoinStatus(groupId, JoinStatus.JOIN);
	}

	private void setRole(GroupUser groupUser, RoleType roleType){
		Role role = roleService.getRole(roleType);

		GroupUserRole groupUserRole = GroupUserRole.builder()
			.groupUser(groupUser)
			.role(role)
			.level(role.getLevel())
			.name(role.getName())
			.build();

		groupUserRoleRepository.save(groupUserRole);
	}

}
