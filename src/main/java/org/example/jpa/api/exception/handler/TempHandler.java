package org.example.jpa.api.exception.handler;

import lombok.*;
import org.example.jpa.api.code.BaseErrorCode;
import org.example.jpa.api.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode code) {
        super(code);
    }
}
