package org.example.jpa.domain.member.converter;

import org.example.jpa.domain.foodcategory.repository.entity.FoodCategory;
import org.example.jpa.domain.mapping.MemberPrefer;

import java.util.List;

public class MemberPreferConverter {

    public static List<MemberPrefer> toMemberPreferList(List<FoodCategory> foodCategoryList) {

        return (List<MemberPrefer>) foodCategoryList.stream()
            .map(foodCategory -> MemberPrefer.builder()
                .foodCategory(foodCategory)
                .build()
            ).toList();
    }
}
