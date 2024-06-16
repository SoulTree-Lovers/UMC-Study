package org.example.jpa.domain.foodcategory.repository;

import org.example.jpa.domain.foodcategory.repository.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {

}
