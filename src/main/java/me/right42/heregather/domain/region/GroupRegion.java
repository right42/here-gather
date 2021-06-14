package me.right42.heregather.domain.region;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.right42.heregather.domain.common.BaseEntity;
import me.right42.heregather.domain.group.Group;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GroupRegion extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "group_region_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "group_id")
	private Group group;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "region_id")
	private Region region;

	private Integer priority;
}