package org.example.jpa.domain.region.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.jpa.domain.region.controller.RegisterStoreRequestDto;
import org.example.jpa.domain.region.repository.RegionRepository;
import org.example.jpa.domain.region.repository.entity.Region;
import org.example.jpa.domain.store.repository.StoreRepository;
import org.example.jpa.domain.store.repository.entity.Store;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService{

    private final RegionRepository regionRepository;

    private final StoreRepository storeRepository;

    @PostConstruct
    private void init() {
        regionRepository.save(Region.builder()
                .name("서울")
            .build());
    }

    public Region registerStore(RegisterStoreRequestDto registerStoreRequestDto) {

        Region region = regionRepository.findById(registerStoreRequestDto.getRegionId()).orElseThrow();

        Store store = Store.builder()
            .name(registerStoreRequestDto.getStoreName())
            .address(registerStoreRequestDto.getStoreAddress())
            .build();

        region.getStoreList().add(store);
        storeRepository.save(store);

        return regionRepository.save(region);
    }

}
