package me.right42.heregather.domain.interest;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.right42.heregather.domain.common.BaseEntity;

/**
 * 관심 카테고리
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class InterestCategory extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "interest_category_id")
	private Long id;

	private String name;

}
