package me.right42.heregather.web.dto.interst;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.ToString;
import me.right42.heregather.domain.interest.dto.InterestWithGroup;

@Getter
@ToString
public class InterestGroupingDto {

	private final String groupName;

	private final List<SimpleInterestDto> interests;

	public InterestGroupingDto(String key, List<InterestWithGroup> value) {
		this.groupName = key;

		interests = value.stream()
			.map(e -> new SimpleInterestDto(e.getId(), e.getName()))
			.collect(Collectors.toUnmodifiableList());
	}
}
