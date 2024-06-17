package org.example.jpa.domain.region.service;

import org.example.jpa.domain.region.controller.RegisterStoreRequestDto;
import org.example.jpa.domain.region.repository.entity.Region;
import org.example.jpa.domain.store.repository.entity.Store;

import java.util.List;

public interface RegionService {

    Region registerStore(RegisterStoreRequestDto registerStoreRequestDto);

}
