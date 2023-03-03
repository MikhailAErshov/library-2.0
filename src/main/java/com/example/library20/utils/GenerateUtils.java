package com.example.library20.utils;

import com.example.library20.newspaper.NewspaperDto;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.example.library20.book.BookDto;

public class GenerateUtils {

    private static Faker faker = new Faker();

    public static List<BookDto> generateSomeBooks(int count) {
        final List<BookDto> books = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            books.add(new BookDto(faker.book().title(), faker.book().author()));
        }
        return books;
    }

    public static List<NewspaperDto> generateSomeNewsPapers(int count) {
        final List<NewspaperDto> newsPapers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            newsPapers.add(new NewspaperDto(faker.book().title(), faker.date().past(20, TimeUnit.DAYS).toInstant()));
        }
        return newsPapers;
    }
}

