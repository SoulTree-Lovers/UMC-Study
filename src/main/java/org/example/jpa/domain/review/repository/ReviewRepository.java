package org.example.jpa.domain.review.repository;

import org.example.jpa.domain.review.repository.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
