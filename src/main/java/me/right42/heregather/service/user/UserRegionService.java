package me.right42.heregather.service.user;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.region.Region;
import me.right42.heregather.domain.region.repository.RegionRepository;
import me.right42.heregather.domain.user.UserRegion;
import me.right42.heregather.domain.user.User;
import me.right42.heregather.domain.user.repository.UserRegionRepository;
import me.right42.heregather.exception.ApplicationException;
import me.right42.heregather.web.dto.region.RegionRequestDto;

@Service
@RequiredArgsConstructor
public class UserRegionService {

	private final UserRegionRepository userRegionRepository;

	private final RegionRepository regionRepository;

	@Transactional
	public void changeUserRegion(User user, List<RegionRequestDto> regions) {
		List<UserRegion> oldUserRegions = userRegionRepository.findAllByUser(user);

		userRegionRepository.deleteAll(oldUserRegions);

		List<UserRegion> userRegions =
			regions.stream()
			.map(regionRequestDto -> {
				Region region = regionRepository.findById(regionRequestDto.getRegionId())
					.orElseThrow(ApplicationException::new);

				return UserRegion.builder()
					.region(region)
					.user(user)
					.build();
			})
			.collect(toUnmodifiableList());

		userRegionRepository.saveAll(userRegions);
	}
}
