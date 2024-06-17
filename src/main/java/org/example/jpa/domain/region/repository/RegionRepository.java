package org.example.jpa.domain.region.repository;

import org.example.jpa.domain.region.repository.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {


}
