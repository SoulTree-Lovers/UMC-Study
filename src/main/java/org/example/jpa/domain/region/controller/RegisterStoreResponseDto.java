package org.example.jpa.domain.region.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jpa.domain.region.repository.entity.Region;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterStoreResponseDto {

    private String regionName;

}
