package org.example.jpa.domain.review.controller.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ReviewPreviewDto(

    String ownerNickname, // 일단 닉네임으로만 리뷰 찾기
    Float score,
    String body,
    LocalDate createdAt

) {
}
