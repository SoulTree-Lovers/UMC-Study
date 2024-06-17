package org.example.jpa.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.example.jpa.domain.foodcategory.service.FoodCategoryService;
import org.example.jpa.domain.store.service.StoreService;
import org.example.jpa.validation.annotation.ExistCategories;
import org.example.jpa.validation.annotation.ExistStore;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreService storeService;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {
        return storeService.isValid(storeId, context);
    }
}