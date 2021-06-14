package me.right42.heregather.domain.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.user.UserRegion;
import me.right42.heregather.domain.user.User;

public interface UserRegionRepository extends JpaRepository<UserRegion, Long> {

	List<UserRegion> findAllByUser(User user);

}
