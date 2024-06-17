package org.example.jpa.domain.store.controller;

import lombok.Builder;

@Builder
public record StoreMissionResponseDto(
    String storeName
) {
}
