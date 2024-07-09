package org.example.jpa.domain.member.repository;

import org.example.jpa.domain.member.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email); // email로 사용자 정보 가져옴

}
