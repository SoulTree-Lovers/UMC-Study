package org.example.jpa.domain.mission.controller;

import lombok.Builder;
import lombok.Data;
import org.example.jpa.domain.mission.enums.MissionStatus;
import org.example.jpa.validation.annotation.ValidMissionStatus;

@Builder
@ValidMissionStatus
public record MissionChangeRequestDto(
    Long missionId,
    MissionStatus toStatus
) {
}
