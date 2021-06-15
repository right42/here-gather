package me.right42.heregather.service.interest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.interest.repository.InterestQueryRepository;
import me.right42.heregather.domain.interest.repository.InterestRepository;
import me.right42.heregather.web.dto.interst.InterestGroupingDto;
import me.right42.heregather.web.dto.interst.InterestResponseDto;

@Service
@RequiredArgsConstructor
public class InterestService {

	private final InterestQueryService interestQueryService;

	@Cacheable(cacheNames = "interestCache")
	public List<InterestGroupingDto> findAll(){
		return interestQueryService.findAllWithGrouping().stream()
			.collect(Collectors.toUnmodifiableList())
		;
	}


}
