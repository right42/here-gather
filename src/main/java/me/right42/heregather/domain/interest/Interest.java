package me.right42.heregather.domain.interest;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 관심사
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Interest {

	@Id
	@GeneratedValue
	@Column(name = "interest_id")
	private Long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private InterestCategory category;

}
