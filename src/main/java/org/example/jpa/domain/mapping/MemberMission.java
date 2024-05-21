package org.example.jpa.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import org.example.jpa.common.BaseEntity;
import org.example.jpa.domain.member.repository.entity.Member;
import org.example.jpa.domain.mission.enums.MissionStatus;
import org.example.jpa.domain.mission.repository.entity.Mission;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

}