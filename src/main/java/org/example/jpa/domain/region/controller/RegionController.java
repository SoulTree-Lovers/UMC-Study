package org.example.jpa.domain.region.controller;

import lombok.RequiredArgsConstructor;
import org.example.jpa.api.ApiResponse;
import org.example.jpa.domain.region.converter.RegionConverter;
import org.example.jpa.domain.region.repository.entity.Region;
import org.example.jpa.domain.region.service.RegionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/region")
public class RegionController {

    private final RegionService regionService;

    private final RegionConverter regionConverter;

    @PostMapping("/register-store")
    public ApiResponse<RegisterStoreResponseDto> registerStore(
        @RequestBody RegisterStoreRequestDto registerStoreRequestDto
    ) {
        Region region = regionService.registerStore(registerStoreRequestDto);

        return ApiResponse.onSuccess(regionConverter.toRegisterStoreResponseDto(region));
    }


}
