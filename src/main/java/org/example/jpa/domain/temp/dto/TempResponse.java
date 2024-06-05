package org.example.jpa.domain.temp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TempResponse {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class TempTestDTO {
        String testString;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class TempExceptionDTO {
        Integer flag;
    }


}
