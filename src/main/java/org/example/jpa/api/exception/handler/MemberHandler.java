package org.example.jpa.api.exception.handler;

import org.example.jpa.api.code.BaseErrorCode;
import org.example.jpa.api.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode code) {
        super(code);
    }
}
