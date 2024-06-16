package org.example.jpa.domain.foodcategory.service;

import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.example.jpa.api.code.status.ErrorStatus;
import org.example.jpa.domain.foodcategory.repository.FoodCategoryRepository;
import org.example.jpa.domain.foodcategory.repository.entity.FoodCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    @PostConstruct
    public void init() {
        foodCategoryRepository.save(FoodCategory.builder()
            .name("한식")
            .build()
        );

        foodCategoryRepository.save(FoodCategory.builder()
            .name("양식")
            .build()
        );

        foodCategoryRepository.save(FoodCategory.builder()
            .name("중식")
            .build()
        );

        foodCategoryRepository.save(FoodCategory.builder()
            .name("일식")
            .build()
        );
    }

    public boolean idValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
            .allMatch(foodCategoryRepository::existsById);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
