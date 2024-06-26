package org.example.jpa.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jpa.api.ApiResponse;
import org.example.jpa.domain.mission.controller.dto.MissionChangeRequestDto;
import org.example.jpa.domain.mission.controller.dto.MissionChangeResponseDto;
import org.example.jpa.domain.mission.controller.dto.MissionPreviewListDto;
import org.example.jpa.domain.mission.converter.MissionConverter;
import org.example.jpa.domain.mission.repository.entity.Mission;
import org.example.jpa.domain.mission.service.MissionService;
import org.example.jpa.validation.annotation.CheckPage;
import org.example.jpa.validation.annotation.ExistStore;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
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

    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
        @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
        @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    @GetMapping("/store-missions/{storeId}")
    public ApiResponse<MissionPreviewListDto> getStoreMissionList(
        @ExistStore @PathVariable(name = "storeId") Long storeId,
        @CheckPage @RequestParam(name = "page") Integer page
    ) {
        Page<Mission> storeMissionList = missionService.getStoreMissionList(storeId, page);

        MissionPreviewListDto missionPreviewListDto = missionConverter.toMissionPreviewListDto(storeMissionList);

        return ApiResponse.onSuccess(missionPreviewListDto);
    }

    @Operation(summary = "나의 미션 목록 조회 API", description = "나의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
        @Parameter(name = "memberId", description = "나의 아이디, path variable 입니다!"),
        @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    @GetMapping("/member-missions/{memberId}")
    public ApiResponse<MissionPreviewListDto> getMyMissionList(
        @ExistStore @PathVariable(name = "memberId") Long memberId,
        @CheckPage @RequestParam(name = "page") Integer page
    ) {
        Page<Mission> missionPage = missionService.getMyMissionList(memberId, page);

        MissionPreviewListDto missionPreviewListDto = missionConverter.toMissionPreviewListDto(missionPage);

        return ApiResponse.onSuccess(missionPreviewListDto);
    }

}
