package org.example.jpa.domain.review.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.jpa.domain.member.repository.entity.Member;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Float score;

    @ManyToOne
    private Member member;
}
