package org.example.jpa.api.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorReason {

    private Boolean isSuccess;
    private String code;
    private String message;
    private HttpStatus httpStatus;

}
