package org.example.jpa.domain.store.service;

import jakarta.validation.ConstraintValidatorContext;
import org.example.jpa.domain.store.controller.dto.StoreMissionRequestDto;
import org.example.jpa.domain.store.controller.dto.StoreReviewRequestDto;
import org.example.jpa.domain.store.repository.entity.Store;

public interface StoreService {

    Store addReview(StoreReviewRequestDto storeReviewRequestDto);
    Store addMission(StoreMissionRequestDto storeMissionRequestDto);

    Store findStore(Long id);
    boolean isValid(Long storeId, ConstraintValidatorContext context);
}
