package org.example.jpa.domain.reviewimage.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.jpa.common.BaseEntity;
import org.example.jpa.domain.review.repository.entity.Review;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

}
