package com.example.library20.sortAndSearch;

import com.example.library20.sortAndSearch.SearchOperations;
import com.example.library20.book.BookDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchOperationsController {
    private final SearchOperations operationsHandler;

    @GetMapping(path = "/getByInfo")
    public Optional<BookDto> getBookByInfo(
            @RequestParam @NonNull String name,
            @RequestParam(required = false) String author
    ) {
        if (Objects.nonNull(author)) {
            return operationsHandler.getBookByNameAndAuthor(name, author);
        } else {
            return operationsHandler.getBookByName(name);
        }
    }
}
