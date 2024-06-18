package org.example.jpa.domain.store.controller.dto;

import lombok.Builder;

@Builder
public record StoreMissionResponseDto(
    String storeName
) {
}
