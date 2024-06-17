package org.example.jpa.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.example.jpa.domain.mission.controller.MissionChangeRequestDto;
import org.example.jpa.domain.mission.service.MissionService;
import org.example.jpa.domain.store.service.StoreService;
import org.example.jpa.validation.annotation.ExistStore;
import org.example.jpa.validation.annotation.ValidMissionStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionStatusValidator implements ConstraintValidator<ValidMissionStatus, MissionChangeRequestDto> {

    private final MissionService missionService;

    @Override
    public void initialize(ValidMissionStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MissionChangeRequestDto missionChangeRequestDto, ConstraintValidatorContext context) {
        return missionService.isValid(missionChangeRequestDto, context);
    }
}