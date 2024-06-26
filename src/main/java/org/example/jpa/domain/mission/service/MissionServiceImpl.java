package org.example.jpa.domain.mission.service;

import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jpa.api.code.status.ErrorStatus;
import org.example.jpa.api.exception.handler.MemberHandler;
import org.example.jpa.api.exception.handler.MissionHandler;
import org.example.jpa.api.exception.handler.StoreHandler;
import org.example.jpa.domain.mapping.membermission.MemberMission;
import org.example.jpa.domain.mapping.membermission.MemberMissionRepository;
import org.example.jpa.domain.member.repository.MemberRepository;
import org.example.jpa.domain.member.repository.entity.Member;
import org.example.jpa.domain.mission.controller.dto.MissionChangeRequestDto;
import org.example.jpa.domain.mission.repository.MissionRepository;
import org.example.jpa.domain.mission.repository.entity.Mission;
import org.example.jpa.domain.store.repository.StoreRepository;
import org.example.jpa.domain.store.repository.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;

    private final StoreRepository storeRepository;

    private final MemberRepository memberRepository;

    private final MemberMissionRepository memberMissionRepository;



    public Mission changeMissionStatus(MissionChangeRequestDto missionChangeRequestDto) {
        Mission mission = missionRepository.findById(missionChangeRequestDto.missionId()).get();

        mission.setMissionStatus(missionChangeRequestDto.toStatus());

        return missionRepository.save(mission);
    }


    @Override
    public boolean isValid(MissionChangeRequestDto missionChangeRequestDto, ConstraintValidatorContext context) {
        Mission mission = missionRepository.findById(missionChangeRequestDto.missionId()).orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        boolean isValid = !(missionChangeRequestDto.toStatus()==mission.getMissionStatus());
        log.info("toStatus: {}", missionChangeRequestDto.toStatus());
        log.info("currentStatus: {}", mission.getMissionStatus());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_STATUS_ALREADY_SAME.toString()).addConstraintViolation();
        }

        return isValid;
    }

    @Override
    public Page<Mission> getStoreMissionList(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Page<Mission> missionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));

        return missionPage;
    }

    @Override
    public Page<Mission> getMyMissionList(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Page<Mission> missionPage = memberMissionRepository.findMissionByMember(member, PageRequest.of(page, 10));

        return missionPage;
    }

}
