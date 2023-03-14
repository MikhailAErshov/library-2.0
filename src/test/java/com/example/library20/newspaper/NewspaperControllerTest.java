package com.example.library20.newspaper;

import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.example.library20.Application.books;
import static com.example.library20.Application.newspapers;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NewspaperControllerTest {
    private final Faker faker = new Faker();

    @Test
    public void shouldAddRandomNewspaperStatusCode() {
        given()
                .when().post("http://localhost:8090/newspaper/add")
                .then().statusCode(200);
    }

    @Test
    public void shouldAddRandomNewspaper() {
        final NewspaperDto newNp =
                new NewspaperDto(faker.book().title(), faker.date().past(20, TimeUnit.DAYS).toInstant());
        newspapers.add(newNp);
        given()
                .when().post("http://localhost:8090/newspaper/add")
                .then().body("name", equalTo(newNp.getName()));

    }
}
