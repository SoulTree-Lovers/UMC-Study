package org.example.jpa.domain.mission.service;

import jakarta.validation.ConstraintValidatorContext;
import org.example.jpa.domain.mission.controller.dto.MissionChangeRequestDto;
import org.example.jpa.domain.mission.repository.entity.Mission;
import org.example.jpa.domain.review.repository.entity.Review;
import org.springframework.data.domain.Page;

public interface MissionService {

    Mission changeMissionStatus(MissionChangeRequestDto missionChangeRequestDto);

    boolean isValid(MissionChangeRequestDto missionChangeRequestDto, ConstraintValidatorContext context);

    Page<Mission> getStoreMissionList(Long storeId, Integer page);

    Page<Mission> getMyMissionList(Long memberId, Integer page);


}
