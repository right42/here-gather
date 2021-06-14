package me.right42.heregather.domain.region;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.right42.heregather.domain.common.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Region extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "region_id")
	private Long id;

	private String name;

	private Integer level;

	@ManyToOne(fetch = LAZY)
	private Region parents;
}
