package org.example.jpa.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.example.jpa.api.code.status.ErrorStatus;
import org.example.jpa.api.exception.handler.StoreHandler;
import org.example.jpa.domain.review.repository.ReviewRepository;
import org.example.jpa.domain.review.repository.entity.Review;
import org.example.jpa.domain.store.repository.StoreRepository;
import org.example.jpa.domain.store.repository.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final StoreRepository storeRepository;


    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Page<Review> storePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));

        return storePage;

    }
}
