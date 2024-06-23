package org.example.jpa.domain.mission.service;

import jakarta.validation.ConstraintValidatorContext;
import org.example.jpa.domain.mission.controller.dto.MissionChangeRequestDto;
import org.example.jpa.domain.mission.repository.entity.Mission;

public interface MissionService {

    Mission changeMissionStatus(MissionChangeRequestDto missionChangeRequestDto);

    boolean isValid(MissionChangeRequestDto missionChangeRequestDto, ConstraintValidatorContext context);

}
