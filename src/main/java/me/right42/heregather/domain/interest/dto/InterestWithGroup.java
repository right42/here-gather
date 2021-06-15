package me.right42.heregather.domain.interest.dto;

import lombok.Data;

@Data
public class InterestWithGroup {

	private Long id;

	private String categoryName;

	private String name;

	public InterestWithGroup(Long id, String categoryName, String name) {
		this.id = id;
		this.categoryName = categoryName;
		this.name = name;
	}
}
