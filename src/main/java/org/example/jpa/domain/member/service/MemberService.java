package org.example.jpa.domain.member.service;

import org.example.jpa.domain.member.controller.MemberRequestDto;
import org.example.jpa.domain.member.repository.entity.Member;
import org.springframework.stereotype.Service;

public interface MemberService {


    Member joinMember(MemberRequestDto.JoinDto request);
}
