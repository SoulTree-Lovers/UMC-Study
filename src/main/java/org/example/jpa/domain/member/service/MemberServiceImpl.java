package org.example.jpa.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.example.jpa.api.code.status.ErrorStatus;
import org.example.jpa.api.exception.handler.FoodCategoryHandler;
import org.example.jpa.domain.foodcategory.repository.FoodCategoryRepository;
import org.example.jpa.domain.foodcategory.repository.entity.FoodCategory;
import org.example.jpa.domain.mapping.MemberPrefer;
import org.example.jpa.domain.member.controller.MemberRequestDto;
import org.example.jpa.domain.member.converter.MemberConverter;
import org.example.jpa.domain.member.converter.MemberPreferConverter;
import org.example.jpa.domain.member.repository.MemberRepository;
import org.example.jpa.domain.member.repository.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

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
}
