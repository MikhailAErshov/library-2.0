package com.example.library20.example;

import com.example.library20.book.BookController;
import com.example.library20.book.BookDto;
import com.example.library20.book.BooksOperations;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MockitoTest extends BaseTest{
    Faker faker = new Faker();

    BooksOperations operations = mock(BooksOperations.class);

    private final BookController bookController = new BookController(operations);

    @Test
    public void shouldDeleteBook() {
        final BookDto bookToDelete = testBooks.get(faker.random().nextInt(0, testBooks.size() - 1));

        bookController.deleteBook(bookToDelete.getName());

        verify(operations, times(1)).deleteBook(bookToDelete.getName(), List.of());
    }
}
