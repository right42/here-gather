package me.right42.heregather.domain.group.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.group.Group;
import me.right42.heregather.domain.group.GroupUser;
import me.right42.heregather.domain.user.User;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {

	Optional<GroupUser> findByGroupAndUser(Group group, User user);
}
