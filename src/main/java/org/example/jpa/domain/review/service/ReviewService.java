package org.example.jpa.domain.review.service;

import org.example.jpa.domain.review.repository.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface ReviewService {

    Page<Review> getReviewList(Long storeId, Integer page);

    Page<Review> getMyReviewList(Long memberId, Integer page);


}
