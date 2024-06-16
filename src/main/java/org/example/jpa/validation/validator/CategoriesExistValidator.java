package org.example.jpa.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.example.jpa.api.code.status.ErrorStatus;
import org.example.jpa.domain.foodcategory.repository.FoodCategoryRepository;
import org.example.jpa.domain.foodcategory.service.FoodCategoryService;
import org.example.jpa.validation.annotation.ExistCategories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

    private final FoodCategoryService foodCategoryService;

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        return foodCategoryService.idValid(values, context);
    }
}