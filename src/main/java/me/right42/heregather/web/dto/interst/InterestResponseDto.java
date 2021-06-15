package me.right42.heregather.web.dto.interst;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class InterestResponseDto {

	private final Long id;

	private final String name;

}
