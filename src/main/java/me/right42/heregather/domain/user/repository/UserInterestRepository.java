package me.right42.heregather.domain.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.interest.user.UserInterest;
import me.right42.heregather.domain.user.User;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {
	List<UserInterest> findByUser(User user);
}
