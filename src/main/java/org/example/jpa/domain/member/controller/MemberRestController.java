package org.example.jpa.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jpa.api.ApiResponse;
import org.example.jpa.domain.member.converter.MemberConverter;
import org.example.jpa.domain.member.repository.entity.Member;
import org.example.jpa.domain.member.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDto.JoinResultDTO> join(@RequestBody @Valid MemberRequestDto.JoinDto request){

        Member member = memberService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));

    }
}