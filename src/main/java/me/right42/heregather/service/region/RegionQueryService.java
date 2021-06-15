package me.right42.heregather.service.region;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.region.Region;
import me.right42.heregather.domain.region.repository.RegionQueryRepository;
import me.right42.heregather.domain.region.repository.RegionRepository;
import me.right42.heregather.web.dto.region.RegionResponse;
import me.right42.heregather.web.dto.region.RegionSearchDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionQueryService {

	private final RegionQueryRepository queryRepository;

	private final RegionRepository regionRepository;

	public List<RegionResponse> searchRegion(RegionSearchDto regionSearchDto) {
		List<Region> findRegion = regionRepository.findByLevelAndNameContains(regionSearchDto.getLevel(),
			regionSearchDto.getRegionName());

		return findRegion.stream()
				.map(e -> new RegionResponse(e.getLevel(), e.getName()))
				.collect(Collectors.toUnmodifiableList());
	}
}
