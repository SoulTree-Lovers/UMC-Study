package org.example.jpa.api.exception.handler;

import org.example.jpa.api.code.BaseErrorCode;
import org.example.jpa.api.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode code) {
        super(code);
    }
}
