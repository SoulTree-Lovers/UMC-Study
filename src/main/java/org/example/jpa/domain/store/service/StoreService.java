package org.example.jpa.domain.store.service;

import jakarta.validation.ConstraintValidatorContext;
import org.example.jpa.domain.mission.controller.MissionChangeRequestDto;
import org.example.jpa.domain.store.controller.StoreMissionRequestDto;
import org.example.jpa.domain.store.controller.StoreReviewRequestDto;
import org.example.jpa.domain.store.repository.entity.Store;

import java.util.List;

public interface StoreService {

    Store addReview(StoreReviewRequestDto storeReviewRequestDto);
    Store addMission(StoreMissionRequestDto storeMissionRequestDto);
    boolean isValid(Long storeId, ConstraintValidatorContext context);
}
