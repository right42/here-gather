package me.right42.heregather.web.dto.group;

import lombok.Data;

@Data
public class GroupUpdateDto {

	private Long id;

	private String name;

	private Integer maximumPeople;

	private String description;

}
