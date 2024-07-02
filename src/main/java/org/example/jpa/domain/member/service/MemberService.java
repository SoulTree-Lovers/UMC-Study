package org.example.jpa.domain.member.service;

import org.example.jpa.domain.member.controller.MemberRequestDto;
import org.example.jpa.domain.member.enums.SocialType;
import org.example.jpa.domain.member.repository.entity.Member;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;

public interface MemberService {


    Member joinMember(MemberRequestDto.JoinDto request);

    void processOAuthUser(String email, SocialType socialType);
}
