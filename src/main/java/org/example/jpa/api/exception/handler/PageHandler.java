package org.example.jpa.api.exception.handler;

import org.example.jpa.api.code.BaseErrorCode;
import org.example.jpa.api.exception.GeneralException;

public class PageHandler extends GeneralException {
    public PageHandler(BaseErrorCode code) {
        super(code);
    }
}
