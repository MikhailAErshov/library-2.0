package com.example.library20.example;

import com.example.library20.book.BookDto;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseTest {

    public static List<BookDto> testBooks = new ArrayList<>();

    @BeforeEach
    public void setBooks() throws IOException {
        final FileReader reader = new FileReader("src/main/resources/books.json");
        Scanner sc = new Scanner(reader);
        JSONArray books = new JSONArray(sc.nextLine());
        sc.close();
        reader.close();
        for (int i = 0; i < books.length(); i++) {
            final String name = books.getJSONObject(i).get("name").toString();
            final String author = books.getJSONObject(i).get("author").toString();
            testBooks.add(new BookDto(name, author));
        }
    }
}
