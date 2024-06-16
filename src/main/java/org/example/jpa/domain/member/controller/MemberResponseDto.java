package org.example.jpa.domain.member.controller;

import lombok.*;

import java.time.LocalDateTime;

public class MemberResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long memberId;
        LocalDateTime createdAt;
    }

}
