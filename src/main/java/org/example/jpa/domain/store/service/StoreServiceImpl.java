package org.example.jpa.domain.store.service;

import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.example.jpa.api.code.status.ErrorStatus;
import org.example.jpa.api.exception.handler.StoreHandler;
import org.example.jpa.domain.mission.repository.MissionRepository;
import org.example.jpa.domain.mission.repository.entity.Mission;
import org.example.jpa.domain.review.repository.ReviewRepository;
import org.example.jpa.domain.review.repository.entity.Review;
import org.example.jpa.domain.store.controller.dto.StoreMissionRequestDto;
import org.example.jpa.domain.store.controller.dto.StoreReviewRequestDto;
import org.example.jpa.domain.store.repository.StoreRepository;
import org.example.jpa.domain.store.repository.entity.Store;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    private final ReviewRepository reviewRepository;

    private final MissionRepository missionRepository;

    @PostConstruct
    private void init() {
        storeRepository.save(Store.builder()
            .name("맛집")
            .address("서울")
            .build());
    }

    public Store addReview(StoreReviewRequestDto storeReviewRequestDto) {
        Review review = Review.builder()
            .body(storeReviewRequestDto.body())
            .score(storeReviewRequestDto.score())
            .build();

        reviewRepository.save(review);

        Store store = storeRepository.findById(storeReviewRequestDto.storeId()).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        store.getReviewList().add(review);

        return storeRepository.save(store);

    }

    public Store addMission(StoreMissionRequestDto storeMissionRequestDto) {
        Mission mission = Mission.builder()
            .reward(storeMissionRequestDto.reward())
            .deadline(storeMissionRequestDto.deadline())
            .missionSpec(storeMissionRequestDto.missionSpec())
            .build();

        missionRepository.save(mission);

        Store store = storeRepository.findById(storeMissionRequestDto.storeId()).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        store.getMissionList().add(mission);

        return storeRepository.save(store);

    }

    @Override
    public Store findStore(Long id) {
        return storeRepository.findById(id).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
    }

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {
        boolean isValid = storeRepository.existsById(storeId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }

}
