package org.example.jpa.domain.store.converter;

import org.example.jpa.domain.store.controller.StoreMissionResponseDto;
import org.example.jpa.domain.store.controller.StoreReviewResponseDto;
import org.example.jpa.domain.store.repository.entity.Store;
import org.springframework.stereotype.Service;

@Service
public class StoreConverter {

    public StoreReviewResponseDto toStoreReviewResponseDto(Store store) {

        return StoreReviewResponseDto.builder()
            .storeName(store.getName())
            .build();
    }

    public StoreMissionResponseDto toStoreMissionResponseDto(Store store) {

        return StoreMissionResponseDto.builder()
            .storeName(store.getName())
            .build();
    }

}
