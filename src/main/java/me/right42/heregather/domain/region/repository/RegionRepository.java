package me.right42.heregather.domain.region.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.right42.heregather.domain.region.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
