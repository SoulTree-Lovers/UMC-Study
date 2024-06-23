package org.example.jpa.domain.mission.converter;

import lombok.RequiredArgsConstructor;
import org.example.jpa.domain.mission.controller.dto.MissionChangeResponseDto;
import org.example.jpa.domain.mission.controller.dto.MissionPreviewDto;
import org.example.jpa.domain.mission.controller.dto.MissionPreviewListDto;
import org.example.jpa.domain.mission.repository.entity.Mission;
import org.example.jpa.domain.store.repository.StoreRepository;
import org.example.jpa.domain.store.service.StoreService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionConverter {

    private final StoreService storeService;

    public MissionChangeResponseDto toMissionChangeResponseDto(Mission mission) {
        return MissionChangeResponseDto.builder()
            .currentStatus(mission.getMissionStatus())
            .build();
    }

    public MissionPreviewDto toMissionPreviewDto(Mission mission) {

        return MissionPreviewDto.builder()
            .reward(mission.getReward())
            .deadline(mission.getDeadline())
            .missionSpec(mission.getMissionSpec())
            .missionStatus(mission.getMissionStatus())
            .build();

    }

    public MissionPreviewListDto toMissionPreviewListDto(Page<Mission> missions) {

        List<MissionPreviewDto> missionPreviewDtoList = missions.stream()
            .map(this::toMissionPreviewDto)
            .toList();

        return MissionPreviewListDto.builder()
            .missionList(missionPreviewDtoList)
            .isLast(missions.isLast())
            .isFirst((missions.isFirst()))
            .totalPage(missions.getTotalPages())
            .totalElements(missions.getTotalElements())
            .listSize(missions.getSize())
            .build();
    }

}
