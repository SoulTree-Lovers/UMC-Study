package org.example.jpa.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.jpa.api.ApiResponse;
import org.example.jpa.domain.review.controller.dto.ReviewPreviewListDto;
import org.example.jpa.domain.review.repository.entity.Review;
import org.example.jpa.domain.review.service.ReviewService;
import org.example.jpa.domain.review.service.ReviewServiceImpl;
import org.example.jpa.validation.annotation.ExistStore;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
        @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
        @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    @GetMapping("/{storeId}/reviews")
    public ApiResponse<ReviewPreviewListDto> getReviewList(
        @ExistStore @PathVariable(name = "storeId") Long storeId,
        @RequestParam(name = "page") Integer page
    ) {
        Page<Review> reviewList = reviewService.getReviewList(storeId, page);

        return null;
    }

}
