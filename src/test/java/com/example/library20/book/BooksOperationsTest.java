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
    @Test
    void shouldAddBookIfListEmpty() {
        List<BookDto> testBooks = generateSomeBooks(0);

        final BookDto result = booksOperations.addBook(testBooks);

        assertThat(result.getName()).isNotNull();
        assertThat(result.getAuthor()).isNotNull();

        assertThat(testBooks).contains(result);
    }
    @Test
    void shouldDeleteBook(){
        List<BookDto> testBooks = generateSomeBooks(5); //сгенерировал 5 книг

        String bookOneToDelete = testBooks.get(1).getName(); //записал имя книги подлежащей удалению

        booksOperations.deleteBook(bookOneToDelete, testBooks); //метод для удаления книги

        assertThat(testBooks).hasSize(4); //проверяем что осталось 4 книги

        BookDto bookTwoToDelete = testBooks.get(testBooks.size() - 1);//помечаем книгу для удаления

        booksOperations.deleteBook(bookTwoToDelete.getName(), testBooks); //метод для удаления книги

        assertThat(testBooks).doesNotContain(bookTwoToDelete); //проверяем что удалили именно ту книгу
    }
}
