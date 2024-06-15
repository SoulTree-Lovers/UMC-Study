package org.example.jpa.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.jpa.api.code.BaseErrorCode;
import org.example.jpa.api.code.ErrorReason;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReason getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReason getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
