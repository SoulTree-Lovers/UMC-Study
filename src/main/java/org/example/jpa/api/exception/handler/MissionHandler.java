package org.example.jpa.api.exception.handler;

import org.example.jpa.api.code.BaseErrorCode;
import org.example.jpa.api.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode code) {
        super(code);
    }
}
