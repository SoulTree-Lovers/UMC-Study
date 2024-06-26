package org.example.jpa.domain.mapping.membermission;

import org.example.jpa.domain.member.repository.entity.Member;
import org.example.jpa.domain.mission.repository.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findAllByMember(Member member, PageRequest pageRequest);

    @Query("SELECT mm.mission FROM MemberMission mm WHERE mm.member = :member")
    Page<Mission> findMissionByMember(@Param("member") Member member, PageRequest pageRequest);

}
