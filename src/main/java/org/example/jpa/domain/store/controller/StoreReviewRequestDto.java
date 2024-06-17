package org.example.jpa.domain.store.controller;

import lombok.Builder;
import org.example.jpa.validation.annotation.ExistStore;

@Builder
public record StoreReviewRequestDto(
    @ExistStore
    Long storeId,
    String body,
    Float score
) {
}
