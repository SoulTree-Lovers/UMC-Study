package org.example.jpa.domain.mission.controller;

import lombok.Builder;
import org.example.jpa.domain.mission.enums.MissionStatus;

@Builder
public record MissionChangeResponseDto(
    MissionStatus currentStatus
) {
}
