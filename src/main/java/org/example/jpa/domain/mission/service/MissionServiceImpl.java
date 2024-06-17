package org.example.jpa.domain.mission.service;

import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jpa.api.code.status.ErrorStatus;
import org.example.jpa.api.exception.handler.MissionHandler;
import org.example.jpa.domain.mission.controller.MissionChangeRequestDto;
import org.example.jpa.domain.mission.enums.MissionStatus;
import org.example.jpa.domain.mission.repository.MissionRepository;
import org.example.jpa.domain.mission.repository.entity.Mission;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;


    public Mission changeMissionStatus(MissionChangeRequestDto missionChangeRequestDto) {
        Mission mission = missionRepository.findById(missionChangeRequestDto.missionId()).get();

        mission.setMissionStatus(missionChangeRequestDto.toStatus());

        return missionRepository.save(mission);
    }


    @Override
    public boolean isValid(MissionChangeRequestDto missionChangeRequestDto, ConstraintValidatorContext context) {
        Mission mission = missionRepository.findById(missionChangeRequestDto.missionId()).orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        boolean isValid = !(missionChangeRequestDto.toStatus()==mission.getMissionStatus());
        log.info("toStatus: {}", missionChangeRequestDto.toStatus());
        log.info("currentStatus: {}", mission.getMissionStatus());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_STATUS_ALREADY_SAME.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
