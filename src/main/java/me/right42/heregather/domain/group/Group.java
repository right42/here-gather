package me.right42.heregather.domain.group;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.right42.heregather.domain.common.BaseEntity;

/**
 * 모임
 */

@Entity
@Table(name = "groups")
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

	public boolean hasJoined(Long joinPeopleCount) {
		return this.maximumPeople > joinPeopleCount + 1;
	}

	public void change(String name, Integer maximumPeople, String description) {
		this.name = name;
		this.description = description;
		this.maximumPeople = maximumPeople;
	}
}
