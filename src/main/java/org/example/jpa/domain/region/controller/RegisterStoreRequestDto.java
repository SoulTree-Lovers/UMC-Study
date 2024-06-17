package org.example.jpa.domain.region.controller;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jpa.domain.region.repository.entity.Region;
import org.example.jpa.domain.store.repository.entity.Store;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterStoreRequestDto {

    private Long regionId;

    private String storeName;

    private String storeAddress;

}
