package org.example.jpa.domain.mission.converter;

import lombok.RequiredArgsConstructor;
import org.example.jpa.domain.mission.controller.MissionChangeResponseDto;
import org.example.jpa.domain.mission.repository.entity.Mission;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionConverter {

    public MissionChangeResponseDto toMissionChangeResponseDto(Mission mission) {
        return MissionChangeResponseDto.builder()
            .currentStatus(mission.getMissionStatus())
            .build();
    }

}
