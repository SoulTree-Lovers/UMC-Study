package org.example.jpa.domain.mission.repository;

import org.example.jpa.domain.mission.repository.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
