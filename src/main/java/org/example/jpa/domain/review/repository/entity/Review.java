package org.example.jpa.domain.review.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.jpa.common.BaseEntity;
import org.example.jpa.domain.member.repository.entity.Member;
import org.example.jpa.domain.reviewimage.repository.entity.ReviewImage;
import org.example.jpa.domain.store.repository.entity.Store;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
//@DependsOn(value = "member")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ReviewImage> reviewImageList = new ArrayList<>();

}