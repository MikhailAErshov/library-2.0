package com.example.library20.dto;

import lombok.Getter;

public class CategoryDto {

    @Getter
    private String name;

    public CategoryDto(String name) {
        this.name = name;
    }
}
