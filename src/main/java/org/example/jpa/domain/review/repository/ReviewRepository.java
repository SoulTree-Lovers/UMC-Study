package org.example.jpa.domain.review.repository;

import org.example.jpa.domain.member.repository.entity.Member;
import org.example.jpa.domain.review.repository.entity.Review;
import org.example.jpa.domain.store.repository.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    Page<Review> findAllByMember(Member member, PageRequest pageRequest);

}
