package org.example.jpa.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.example.jpa.api.code.status.ErrorStatus;
import org.example.jpa.api.exception.handler.FoodCategoryHandler;
import org.example.jpa.api.exception.handler.MemberHandler;
import org.example.jpa.domain.foodcategory.repository.FoodCategoryRepository;
import org.example.jpa.domain.foodcategory.repository.entity.FoodCategory;
import org.example.jpa.domain.mapping.MemberPrefer;
import org.example.jpa.domain.member.controller.MemberRequestDto;
import org.example.jpa.domain.member.converter.MemberConverter;
import org.example.jpa.domain.member.converter.MemberPreferConverter;
import org.example.jpa.domain.member.enums.SocialType;
import org.example.jpa.domain.member.repository.MemberRepository;
import org.example.jpa.domain.member.repository.entity.Member;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl extends DefaultOAuth2UserService implements MemberService {

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDto.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
            .map(category -> {
                return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
            }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    @Override
    @Transactional
    public void processOAuthUser(String email, SocialType socialType) {
        // 사용자가 이미 존재하는지 확인
        Member existingMember = memberRepository.findByEmail(email)
            .orElse(null);

        if (existingMember == null) {
            // 새로운 사용자라면 Member 객체 생성
            Member newMember = Member.builder()
                .email(email)
                .socialType(socialType)
                .build();
            memberRepository.save(newMember);
        } else {
            // 기존 사용자라면 SocialType을 해당 소셜 타입으로 업데이트
            existingMember.setSocialType(socialType);
            memberRepository.save(existingMember);
        }
    }


}
