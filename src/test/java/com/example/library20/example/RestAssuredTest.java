package com.example.library20.example;

import com.example.library20.book.BookDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredTest extends BaseTest{

    private static RequestSpecification request;

    private final Faker faker = new Faker();

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:8090";
    }

    @BeforeEach
    public void setRequest() {
        request = RestAssured.given();
    }

    @Test
    public void shouldGetBooks() {
        when()
                .get("/book/getAll")
                .then()
                .statusCode(200)
                .body("name",  equalTo(testBooks.stream().map(BookDto::getName).toList()));
    }

    @Test
    public void shouldGetBook() {
        when()
                .get("/book/get/1")
                .then()
                .statusCode(200)
                .body("name",  equalTo(testBooks.get(1).getName()),
                        "author", equalTo(testBooks.get(1).getAuthor()));
    }

    @Test
    public void shouldDeleteBook() {
        final BookDto bookToDelete = testBooks.get(faker.random().nextInt(0, testBooks.size() - 1));

        Response response = request.param("name", bookToDelete.getName()).delete("/book/delete");

        assertThat(response.statusCode()).isEqualTo(204);
    }
}
