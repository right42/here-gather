package me.right42.heregather.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.right42.heregather.domain.user.User;
import me.right42.heregather.domain.user.repository.UserRepository;
import me.right42.heregather.domain.user.type.UserStatus;
import me.right42.heregather.domain.user.type.UserType;
import me.right42.heregather.web.dto.user.UserRegisterDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;

	private final UserRegionService userRegionService;

	private final UserInterestService userInterestService;

	@Transactional
	public User register(UserRegisterDto userRegisterDto){

		User user = User.builder()
			.userName(userRegisterDto.getName())
			.userStatus(UserStatus.ACTIVE)
			.userType(UserType.NAVER)
			.build();

		User savedUser = userRepository.save(user);

		userRegionService.changeUserRegion(savedUser, userRegisterDto.getRegions());
		userInterestService.changeUserInterest(savedUser, userRegisterDto.getInterests());

		return savedUser;
	}
}
