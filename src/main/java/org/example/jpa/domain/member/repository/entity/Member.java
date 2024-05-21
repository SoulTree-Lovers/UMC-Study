package org.example.jpa.domain.member.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.jpa.domain.member.enums.Gender;
import org.example.jpa.domain.member.enums.MemberStatus;
import org.example.jpa.domain.member.enums.SocialType;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String specAddress;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private LocalDate inactiveDate;

    private String email;

    private Integer point;
}