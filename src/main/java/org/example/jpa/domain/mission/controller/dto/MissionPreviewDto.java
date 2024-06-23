package org.example.jpa.domain.mission.controller.dto;

import lombok.Builder;
import org.example.jpa.domain.mission.enums.MissionStatus;

import java.time.LocalDate;

@Builder
public record MissionPreviewDto (

    Integer reward,
    LocalDate deadline,
    String missionSpec,
    MissionStatus missionStatus

) {
}
