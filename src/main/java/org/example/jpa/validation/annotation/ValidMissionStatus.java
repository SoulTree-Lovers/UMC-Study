package org.example.jpa.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.jpa.validation.validator.MissionStatusValidator;
import org.example.jpa.validation.validator.StoreExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionStatusValidator.class)
@Target( {ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMissionStatus {

    String message() default "이미 해당 상태입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}