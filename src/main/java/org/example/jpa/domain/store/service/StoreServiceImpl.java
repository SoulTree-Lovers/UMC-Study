package org.example.jpa.domain.store.service;

import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.example.jpa.api.code.status.ErrorStatus;
import org.example.jpa.api.exception.handler.MemberHandler;
import org.example.jpa.api.exception.handler.StoreHandler;
import org.example.jpa.domain.mapping.membermission.MemberMission;
import org.example.jpa.domain.member.repository.MemberRepository;
import org.example.jpa.domain.member.repository.entity.Member;
import org.example.jpa.domain.mission.repository.MissionRepository;
import org.example.jpa.domain.mission.repository.entity.Mission;
import org.example.jpa.domain.review.repository.ReviewRepository;
import org.example.jpa.domain.review.repository.entity.Review;
import org.example.jpa.domain.store.controller.dto.StoreMissionRequestDto;
import org.example.jpa.domain.store.controller.dto.StoreReviewRequestDto;
import org.example.jpa.domain.store.repository.StoreRepository;
import org.example.jpa.domain.store.repository.entity.Store;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    private final ReviewRepository reviewRepository;

    private final MissionRepository missionRepository;

    private final MemberRepository memberRepository;

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
            .member(memberRepository.findById(storeReviewRequestDto.memberId()).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND)))
            .store(storeRepository.findById(storeReviewRequestDto.storeId()).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND)))
            .build();

        reviewRepository.save(review);

        Store store = storeRepository.findById(storeReviewRequestDto.storeId()).get(); // 위에서 이미 가게가 존재한다는 것을 검증함

        store.getReviewList().add(review);

        return storeRepository.save(store);

    }

    public Store addMission(StoreMissionRequestDto storeMissionRequestDto) {
        Mission mission = Mission.builder()
            .reward(storeMissionRequestDto.reward())
            .deadline(storeMissionRequestDto.deadline())
            .missionSpec(storeMissionRequestDto.missionSpec())
            .store(storeRepository.findById(storeMissionRequestDto.storeId()).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND)))
            .memberMissionList(new ArrayList<>())
            .build();

        Member member = memberRepository.findById(storeMissionRequestDto.MemberId()).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        MemberMission memberMission = MemberMission.builder()
            .member(member)
            .mission(mission)
            .status(mission.getMissionStatus())
            .build();

        member.getMemberMissionList().add(memberMission);
        mission.getMemberMissionList().add(memberMission);

        missionRepository.save(mission);

        Store store = storeRepository.findById(storeMissionRequestDto.storeId()).get(); // 위에서 이미 검증

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
