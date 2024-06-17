package org.example.jpa.domain.region.converter;

import org.example.jpa.domain.region.controller.RegisterStoreResponseDto;
import org.example.jpa.domain.region.repository.entity.Region;
import org.springframework.stereotype.Service;

@Service
public class RegionConverter {

    public RegisterStoreResponseDto toRegisterStoreResponseDto(Region region) {
        return RegisterStoreResponseDto.builder()
            .regionName(region.getName())
            .build();
    }

}
