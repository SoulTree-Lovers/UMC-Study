package org.example.jpa.domain.member.converter;

import org.example.jpa.domain.member.controller.MemberRequestDto;
import org.example.jpa.domain.member.controller.MemberResponseDto;
import org.example.jpa.domain.member.enums.Gender;
import org.example.jpa.domain.member.repository.entity.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponseDto.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDto.JoinResultDTO.builder()
            .memberId(member.getId())
            .createdAt(LocalDateTime.now())
            .build();
    }

    public static Member toMember(MemberRequestDto.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
            .address(request.getAddress())
            .specAddress(request.getSpecAddress())
            .gender(gender)
            .name(request.getName())
            .memberPreferList(new ArrayList<>())
            .build();
    }


}