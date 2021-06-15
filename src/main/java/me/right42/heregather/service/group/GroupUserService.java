package me.right42.heregather.service.group;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.group.Group;
import me.right42.heregather.domain.group.GroupUser;
import me.right42.heregather.domain.group.repository.GroupUserRepository;
import me.right42.heregather.domain.group.repository.GroupUserRoleRepository;
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

	private final RoleService roleService;

	@Transactional
	public void join(User user, Group group) {
		GroupUser groupUser = GroupUser.builder()
			.group(group)
			.user(user)
			.joinStatus(JoinStatus.JOIN)
			.build();

		GroupUser savedGroupUser = groupUserRepository.save(groupUser);
		Role role = roleService.getRole(RoleType.MANAGER);

		GroupUserRole groupUserRole = GroupUserRole.builder()
			.groupUser(savedGroupUser)
			.role(role)
			.level(role.getLevel())
			.name(role.getName())
			.build();

		groupUserRoleRepository.save(groupUserRole);
	}
}
