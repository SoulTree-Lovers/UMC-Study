package org.example.jpa.domain.temp.converter;

import org.example.jpa.domain.temp.dto.TempResponse;

public class TempConverter {

    public static TempResponse.TempTestDTO toTempTestDTO() {
        return TempResponse.TempTestDTO.builder()
            .testString("테스트 응답 메시지")
            .build();
    }

    public static TempResponse.TempExceptionDTO toTempExceptionDTO(Integer flag) {
        return TempResponse.TempExceptionDTO.builder()
            .flag(flag)
            .build();
    }
}
