package org.example.jpa.domain.member.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.example.jpa.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDto {

    @Getter
    public static class JoinDto{
        @Email
        String email;
        @NotBlank @Size(min = 4, max = 20)
        String password;
        @NotBlank
        String name;
        @NotNull
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String specAddress;
        @ExistCategories
        List<Long> preferCategory;
    }

}
