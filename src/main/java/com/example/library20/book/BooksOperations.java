package com.example.library20.book;


import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static com.example.library20.Application.books;
import static com.example.library20.Application.writeBookData;

@Component
@RequiredArgsConstructor
public class BooksOperations {

    final Faker faker = new Faker();

    public BookDto addBook(List<BookDto> books) {
        final BookDto newBook = new BookDto(faker.book().title(), faker.book().author());
        books.add(newBook);
        writeBookData(books);
        return newBook;
    }

    public void deleteBook(String name, List<BookDto> books) {
        final List<BookDto> booksToDelete =
                books.stream().filter(b -> Objects.equals(b.getName(), name)).toList();
        booksToDelete.forEach(b -> books.remove(b));

        writeBookData(books);
    }

    public BookDto addBook(BookDto bookDto){
        books.add(bookDto);
        writeBookData(books);
        return bookDto;

    }

}
