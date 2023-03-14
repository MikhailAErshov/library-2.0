package com.example.library20.book;

import com.example.library20.dto.CategoryDto;
import lombok.Data;

@Data
public class BookDto {

    private String name;

    private String author;

    private CategoryDto category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto categoryDto) {
        if (categoryDto.getName() != null) {
            this.category = categoryDto;
        } else {
            System.out.println("Не получилось привязать категорию, категория не содержит имени");
        }
    }

    public BookDto(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public BookDto(){

    }

}
