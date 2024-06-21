package org.example.jpa.domain.review.controller.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ReviewPreviewListDto(

    List<ReviewPreviewDto> reviewList,
    Integer listSize,
    Integer totalPage,
    Long totalElements,
    Boolean isFirst,
    Boolean isLast

) {
}
