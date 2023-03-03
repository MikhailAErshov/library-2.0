package com.example.library20.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static com.example.library20.utils.GenerateUtils.generateSomeBooks;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BooksOperationsTest {

    private final BooksOperations booksOperations = new BooksOperations();

    @Test
    void shouldAddBook() {
        List<BookDto> testBooks = generateSomeBooks(3);

        final BookDto result = booksOperations.addBook(testBooks);

        assertThat(result.getName()).isNotNull();
        assertThat(result.getAuthor()).isNotNull();

        //assertThat(testBooks.contains(result)).isTrue();
        assertThat(testBooks).contains(result);
    }
}