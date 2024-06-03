package org.example.jpa.api.code.status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.example.jpa.api.code.BaseCode;
import org.example.jpa.api.code.Reason;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반적인 응답
    _OK(HttpStatus.OK, "COMMON200", "요청이 성공적으로 처리되었습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public Reason getReason() {
        return Reason.builder()
            .isSuccess(true)
            .code(code)
            .message(message)
            .build();
    }

    @Override
    public Reason getReasonHttpStatus() {
        return Reason.builder()
            .isSuccess(true)
            .code(code)
            .message(message)
            .httpStatus(httpStatus)
            .build();
    }
}
