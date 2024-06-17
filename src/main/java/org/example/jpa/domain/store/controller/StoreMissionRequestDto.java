package org.example.jpa.domain.store.controller;

import lombok.Builder;
import org.example.jpa.validation.annotation.ExistStore;

import java.time.LocalDate;

@Builder
public record StoreMissionRequestDto(
    @ExistStore
    Long storeId,
    Integer reward,
    LocalDate deadline,
    String missionSpec
) {
}
