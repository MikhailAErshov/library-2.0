package com.example.library20.sortAndSearch;

import com.example.library20.book.BookDto;
import com.example.library20.dto.CategoryDto;
import com.example.library20.newspaper.NewspaperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.library20.Application.books;
import static com.example.library20.Application.newspapers;

@Component
@RequiredArgsConstructor
public class SortOperations {

    private boolean checkIfBookHasCategory(BookDto book) {
        if (book.getClass() == NewspaperDto.class) {
            System.out.println("Газеты не сортируются по категориям");
            return false;
        } else {
            return book.getCategory() != null;
        }
    }

    public Set<CategoryDto> getBooksCategories(List<BookDto> books) {
        final Set<CategoryDto> categories = new HashSet<>();
        books.forEach(book -> {
            if (checkIfBookHasCategory(book)) {
                categories.add(book.getCategory());
            }
        });
        return categories;
    }

    public List<BookDto> getBooksByCategory(CategoryDto category) {
        Set<CategoryDto> categories = getBooksCategories(books);
        List<BookDto> booksOfCategory = new ArrayList<>();
        if (categories.contains(category)) {
            books.forEach(book -> {
                        if (book.getCategory() == category) {
                            booksOfCategory.add(book);
                        }
                    }
            );
            return booksOfCategory;
        } else {
            System.out.println("В библиотеке нет книг с заданной категорией");
            return null;
        }
    }

    public List<NewspaperDto> sortNewspapersByDate(List<NewspaperDto> newspaperDtos) {
        newspaperDtos.sort(Comparator.comparing(NewspaperDto::getDate).reversed());
        return newspaperDtos;
    }

    public List<NewspaperDto> sortNewspapersFromDate(Instant date) {
        final List<NewspaperDto> filteredNp = new ArrayList<>(
                newspapers.stream().filter(np -> np.getDate().isAfter(date)).toList()
        );
        return sortNewspapersByDate(filteredNp);
    }
}
