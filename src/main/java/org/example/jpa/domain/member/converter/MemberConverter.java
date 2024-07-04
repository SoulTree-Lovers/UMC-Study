package org.example.jpa.domain.member.converter;

import lombok.RequiredArgsConstructor;
import org.example.jpa.domain.member.controller.MemberRequestDto;
import org.example.jpa.domain.member.controller.MemberResponseDto;
import org.example.jpa.domain.member.enums.Gender;
import org.example.jpa.domain.member.repository.entity.Member;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RequiredArgsConstructor
public class MemberConverter {

//    private static BCryptPasswordEncoder bCryptPasswordEncoder;

    public static MemberResponseDto.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDto.JoinResultDTO.builder()
            .memberId(member.getId())
            .createdAt(LocalDateTime.now())
            .build();
    }

    public static Member toMember(MemberRequestDto.JoinDto request){

        Gender gender = switch (request.getGender()) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            case 3 -> Gender.NONE;
            default -> null;
        };

        Integer age = LocalDate.now().getYear() - request.getBirthYear() + 1;


        return Member.builder()
            .address(request.getAddress())
            .specAddress(request.getSpecAddress())
            .gender(gender)
            .name(request.getName())
            .email(request.getEmail())
            .password(request.getPassword())
            .age(age)
            .memberPreferList(new ArrayList<>())
            .build();
    }


}