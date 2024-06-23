package org.example.jpa.domain.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jpa.api.ApiResponse;
import org.example.jpa.domain.mission.controller.dto.MissionChangeRequestDto;
import org.example.jpa.domain.mission.controller.dto.MissionChangeResponseDto;
import org.example.jpa.domain.mission.converter.MissionConverter;
import org.example.jpa.domain.mission.repository.entity.Mission;
import org.example.jpa.domain.mission.service.MissionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionController {

    private final MissionService missionService;

    private final MissionConverter missionConverter;

    @PostMapping("/change-status-to-challenging")
    public ApiResponse<MissionChangeResponseDto> changeStatus(
        @RequestBody @Valid MissionChangeRequestDto missionChangeRequestDto
    ) {

        Mission mission = missionService.changeMissionStatus(missionChangeRequestDto);

        return ApiResponse.onSuccess(missionConverter.toMissionChangeResponseDto(mission));

    }


}
