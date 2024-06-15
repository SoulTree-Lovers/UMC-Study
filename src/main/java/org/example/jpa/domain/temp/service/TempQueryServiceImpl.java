package org.example.jpa.domain.temp.service;

import org.example.jpa.api.code.status.ErrorStatus;
import org.example.jpa.api.exception.handler.TempHandler;
import org.springframework.stereotype.Service;

@Service
public class TempQueryServiceImpl implements TempQueryService {
    @Override
    public void checkFlag(Integer flag) {
        if (flag == 1) {
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
