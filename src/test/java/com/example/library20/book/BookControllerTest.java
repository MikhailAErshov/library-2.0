package com.example.library20.book;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.library20.Application.books;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class BookControllerTest {

    private final Faker faker = new Faker();
    private RequestSpecification request;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8081";
    }

    @BeforeEach
    public void setClient() {
        request = RestAssured.given();
    }

    @Test
    public void shouldAddRandomBook() {
        Response response = request.post("/book/add");

        response.then().statusCode(200);
    }

    @Test
    public void shouldGetBooksTest() {
        final BookDto book = new BookDto(faker.book().title(), faker.book().author());
        books.add(book);

        Response response = request.get("/book/get/{index}", 0);

        response.then().statusCode(200);
        response.then().body("name", equalTo(book.getName()),
                "author", equalTo(book.getAuthor()));
    }

    @Test
    public void shouldGetBooksTestAnalog() {
        final BookDto book = new BookDto(faker.book().title(), faker.book().author());
        books.add(book);

        when()
                .get("/book/get/{index}", 0)
                .then()
                .statusCode(200)
                .body("name", equalTo(book.getName()),
                        "author", equalTo(book.getAuthor()));
    }
}
