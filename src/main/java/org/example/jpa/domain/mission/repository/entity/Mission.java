package org.example.jpa.domain.mission.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.jpa.common.BaseEntity;
import org.example.jpa.domain.mapping.membermission.MemberMission;
import org.example.jpa.domain.mission.enums.MissionStatus;
import org.example.jpa.domain.store.repository.entity.Store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer reward;

    private LocalDate deadline;

    private String missionSpec;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(15) DEFAULT 'NONE'")
    private MissionStatus missionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();
}