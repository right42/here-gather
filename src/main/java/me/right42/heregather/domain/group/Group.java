package me.right42.heregather.domain.group;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.right42.heregather.domain.common.BaseEntity;

/**
 * 모임
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Group extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "group_id")
	private Long id;

	private String name;

	private Integer maximumPeople;

	@Lob
	private String description;
}
