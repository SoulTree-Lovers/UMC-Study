package org.example.jpa.api.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PageValidationMethod {

    GET_STORE_REVIEW("getStoreReviewList"),
    GET_MY_REVIEW("getMyReviewList"),
    GET_STORE_MISSION("getStoreMissionList"),
    GET_MY_MISSION("getMyMissionList"),
    ;


    private final String methodName;
}
