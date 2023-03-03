package com.example.library20.sortAndSearch;

import com.example.library20.book.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static com.example.library20.Application.books;

@Component
@RequiredArgsConstructor
public class SearchOperations {

    public Optional<BookDto> getBookByNameAndAuthor(String name, String author) {
        return books.stream().filter(
                book -> Objects.equals(book.getName(), name) && Objects.equals(book.getAuthor(), author)
        ).findFirst();
    }

    public Optional<BookDto> getBookByName(String name) {
        return books.stream().filter(
                book -> Objects.equals(book.getName(), name)
        ).findFirst();
    }
}
