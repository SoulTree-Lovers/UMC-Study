package org.example.jpa.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.example.jpa.api.ApiResponse;
import org.example.jpa.domain.store.controller.dto.StoreMissionRequestDto;
import org.example.jpa.domain.store.controller.dto.StoreMissionResponseDto;
import org.example.jpa.domain.store.controller.dto.StoreReviewRequestDto;
import org.example.jpa.domain.store.controller.dto.StoreReviewResponseDto;
import org.example.jpa.domain.store.converter.StoreConverter;
import org.example.jpa.domain.store.repository.entity.Store;
import org.example.jpa.domain.store.service.StoreService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    private final StoreConverter storeConverter;

    @PostMapping("/add-review")
    public ApiResponse<StoreReviewResponseDto> addReview(
        @RequestBody @Valid StoreReviewRequestDto storeReviewRequestDto

    ) {
        Store store = storeService.addReview(storeReviewRequestDto);

        return ApiResponse.onSuccess(storeConverter.toStoreReviewResponseDto(store));
    }

    @PostMapping("/add-mission")
    public ApiResponse<StoreMissionResponseDto> addMission(
        @RequestBody @Valid StoreMissionRequestDto storeMissionRequestDto

    ) {

        Store store = storeService.addMission(storeMissionRequestDto);

        return ApiResponse.onSuccess(storeConverter.toStoreMissionResponseDto(store));
    }
}
