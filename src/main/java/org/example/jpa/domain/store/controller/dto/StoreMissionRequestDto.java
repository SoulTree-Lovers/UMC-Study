package org.example.jpa.domain.store.controller.dto;

import lombok.Builder;
import org.example.jpa.validation.annotation.ExistStore;

import java.time.LocalDate;

@Builder
public record StoreMissionRequestDto(
    @ExistStore
    Long storeId,
    Long MemberId,
    Integer reward,
    LocalDate deadline,
    String missionSpec
) {
}
