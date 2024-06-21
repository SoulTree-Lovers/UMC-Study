package org.example.jpa.domain.review.converter;

import lombok.RequiredArgsConstructor;
import org.example.jpa.domain.review.controller.dto.ReviewPreviewDto;
import org.example.jpa.domain.review.controller.dto.ReviewPreviewListDto;
import org.example.jpa.domain.review.repository.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ReviewConverter {

    public ReviewPreviewDto toReviewPreviewDto(Review review) {
        return ReviewPreviewDto.builder()
            .ownerNickname(review.getMember().getName())
            .score(review.getScore())
            .body(review.getBody())
            .createdAt(review.getCreatedAt().toLocalDate())
            .build();
    }

    public ReviewPreviewListDto toReviewPreviewListDto(Page<Review> reviewList) {

        List<ReviewPreviewDto> reviewPreviewDtoList = reviewList.stream()
            .map(this::toReviewPreviewDto)
            .toList();

        return ReviewPreviewListDto.builder()
            .isLast(reviewList.isLast())
            .isFirst(reviewList.isFirst())
            .totalPage(reviewList.getTotalPages())
            .totalElements(reviewList.getTotalElements())
            .listSize(reviewPreviewDtoList.size())
            .reviewList(reviewPreviewDtoList)
            .build();

    }



}
