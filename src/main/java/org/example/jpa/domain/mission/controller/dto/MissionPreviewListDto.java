package org.example.jpa.domain.mission.controller.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record MissionPreviewListDto(

    List<MissionPreviewDto> missionList,
    Integer listSize,
    Integer totalPage,
    Long totalElements,
    Boolean isFirst,
    Boolean isLast

) {
}
