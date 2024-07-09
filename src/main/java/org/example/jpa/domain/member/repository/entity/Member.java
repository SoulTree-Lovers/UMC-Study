package org.example.jpa.domain.member.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.jpa.common.BaseEntity;
import org.example.jpa.domain.mapping.MemberAgree;
import org.example.jpa.domain.mapping.membermission.MemberMission;
import org.example.jpa.domain.mapping.MemberPrefer;
import org.example.jpa.domain.member.enums.Gender;
import org.example.jpa.domain.member.enums.MemberStatus;
import org.example.jpa.domain.member.enums.SocialType;
import org.example.jpa.domain.review.repository.entity.Review;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
//@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@Table(name = "member")
@SuperBuilder
//@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) -> Spring Data JPA는 알아서 스네이크 케이스로 바꿔준다는 사실..
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10)")
    private Gender gender;

    private Integer age;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40)
    private String specAddress;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(15) DEFAULT 'ACTIVE'")
    private MemberStatus status;

    private LocalDate inactiveDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10)")
    private SocialType socialType;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ColumnDefault("0")
    private Integer point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

}