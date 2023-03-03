package com.example.library20.example;

import com.example.library20.book.BooksOperations;
import com.example.library20.book.BookDto;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.library20.utils.GenerateUtils.generateSomeBooks;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UnitTests extends BaseTest{

	Faker faker = new Faker();

	@Autowired
	BooksOperations operationsHandler;

	@Test
	public void shouldAddBook() {
		final BookDto result = operationsHandler.addBook(testBooks);

		assertThat(result).isNotNull();
	}

	@Test
	public void shouldDeleteBook() {
		final BookDto bookToDelete = testBooks.get(faker.random().nextInt(0, testBooks.size() - 1));

		operationsHandler.deleteBook(bookToDelete.getName(), testBooks);

		assertThat(testBooks).doesNotContain(bookToDelete);
	}

}
