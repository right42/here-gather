package me.right42.heregather.domain.region.repository;

import static me.right42.heregather.domain.region.QRegion.*;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.region.QRegion;
import me.right42.heregather.web.dto.region.RegionSearchDto;

@Repository
@RequiredArgsConstructor
public class RegionQueryRepository {

	private final JPAQueryFactory query;


}
