package me.right42.heregather.domain.interest.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.interest.dto.InterestWithGroup;

import static me.right42.heregather.domain.interest.QInterest.interest;
import static me.right42.heregather.domain.interest.QInterestCategory.interestCategory;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InterestQueryRepository {

	private final JPAQueryFactory query;

	@Transactional(readOnly = true)
	public List<InterestWithGroup> findInterestWithCategoryName(String interestName) {
		return query
			.select(
				Projections.constructor(
					InterestWithGroup.class,
					interest.id,
					interestCategory.name.as("categoryName"),
					interest.name
				)
			)
			.from(interest)
			.join(interestCategory)
				.on(interest.category.eq(interestCategory))
			.where(nameEq(interestName))
			.fetch()
		;
	}


	private BooleanExpression nameEq(String keyword){
		if (StringUtils.hasText(keyword)) {
			return interest.name.eq(keyword);
		}

		return null;
	}
}
