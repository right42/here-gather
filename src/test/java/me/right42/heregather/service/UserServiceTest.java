package me.right42.heregather.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.right42.heregather.domain.user.User;
import me.right42.heregather.domain.user.type.UserStatus;
import me.right42.heregather.domain.user.type.UserType;
import me.right42.heregather.service.user.UserService;
import me.right42.heregather.web.dto.interst.InterestRequestDto;
import me.right42.heregather.web.dto.region.RegionRequestDto;
import me.right42.heregather.web.dto.user.UserRegisterDto;

@SpringBootTest
class UserServiceTest {

	@Autowired
	UserService userService;

	@Test
	void 회원가입_테스트(){
		String userName = "test";
		UserRegisterDto userRegisterDto = new UserRegisterDto();
		userRegisterDto.setName(userName);

		userRegisterDto.setRegions(makeRegions());
		userRegisterDto.setInterests(makeInterest());

		User registerUser = userService.register(userRegisterDto);

		assertThat(registerUser).isNotNull();
		assertThat(registerUser.getId()).isNotNull();
		assertThat(registerUser.getUserName()).isEqualTo(userName);
		assertThat(registerUser.getUserType()).isEqualTo(UserType.NAVER);
		assertThat(registerUser.getUserStatus()).isEqualTo(UserStatus.ACTIVE);
	}

	private List<InterestRequestDto> makeInterest() {
		InterestRequestDto interestRequestDto = new InterestRequestDto();
		interestRequestDto.setInterestId(1L);
		interestRequestDto.setName("관심사1");

		InterestRequestDto interestRequestDto2 = new InterestRequestDto();
		interestRequestDto2.setInterestId(2L);
		interestRequestDto2.setName("관심사2");

		return Arrays.asList(interestRequestDto, interestRequestDto2);
	}

	public List<RegionRequestDto> makeRegions(){
		RegionRequestDto regionRequestDto = new RegionRequestDto();
		regionRequestDto.setRegionId(1L);
		regionRequestDto.setName("test");

		RegionRequestDto regionRequestDto2 = new RegionRequestDto();
		regionRequestDto2.setRegionId(2L);
		regionRequestDto2.setName("test2");

		return Arrays.asList(regionRequestDto, regionRequestDto2);
	}

}
