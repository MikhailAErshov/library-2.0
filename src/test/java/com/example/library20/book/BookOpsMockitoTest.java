package com.example.library20.book;

import com.example.library20.sortAndSearch.SearchOperations;
import com.example.library20.sortAndSearch.SearchOperationsController;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Optional;

import static com.example.library20.Application.books;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookOpsMockitoTest {

    @Test
    public void mockitoTest() {
        SearchOperations sOps = mock(SearchOperations.class);

        Optional<BookDto> bookDto = books.stream().findFirst();

        when(sOps.getBookByName(anyString())).thenReturn(bookDto);
        //doNothing().when...

        SearchOperationsController sOpController = new SearchOperationsController(sOps);
        sOpController.getBookByInfo("name", null);

        verify(sOps, times(1)).getBookByName("name");
    }
}
