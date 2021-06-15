package me.right42.heregather.service.interest;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.interest.dto.InterestWithGroup;
import me.right42.heregather.domain.interest.repository.InterestQueryRepository;
import me.right42.heregather.web.dto.interst.InterestGroupingDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterestQueryService {

	private final InterestQueryRepository queryRepository;


	public List<InterestGroupingDto> findAllWithGrouping(){
		List<InterestWithGroup> interestWithCategoryName = queryRepository.findInterestWithCategoryName(null);
		return groupNameGrouping(interestWithCategoryName);
	}


	public List<InterestGroupingDto> findByNameWithGrouping(String keyword){
		List<InterestWithGroup> interestWithCategoryName = queryRepository.findInterestWithCategoryName(keyword);
		return groupNameGrouping(interestWithCategoryName);
	}

	private List<InterestGroupingDto> groupNameGrouping(List<InterestWithGroup> interestWithCategoryName) {
		return interestWithCategoryName.stream()
			.collect(groupingBy(InterestWithGroup::getCategoryName))
			.entrySet().stream()
			.map(entry -> new InterestGroupingDto(entry.getKey(), entry.getValue()))
			.collect(Collectors.toUnmodifiableList());
	}
}
