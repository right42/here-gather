package me.right42.heregather.domain.group.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.group.GroupUser;
import me.right42.heregather.domain.group.type.JoinStatus;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {

}
