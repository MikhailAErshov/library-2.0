package com.example.library20.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.library20.Application.books;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    final BooksOperations operationsHandler;

    @GetMapping(path = "/getAll")
    public List<BookDto> getBooks() {
        return books;
    }

    @GetMapping(path = "/get/{index}")
    public BookDto getBooks(@PathVariable("index") Integer index) {
        return books.get(index);
    }

    @PostMapping(path = "/add")
    public BookDto addRandomBook() {
        return operationsHandler.addBook(books);
    }

    @DeleteMapping(path = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@RequestParam String name) {
        operationsHandler.deleteBook(name, books);
    }
}
