package com.example.library20.book;

import com.example.library20.book.BookDto;
import com.example.library20.example.BaseTest;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.library20.Application.books;
import static com.example.library20.example.BaseTest.testBooks;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class SearchOperationsControllerTest {

    private RequestSpecification request;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8090";
    }

    @BeforeEach
    public void setRequest() {
        request = given();
    }

    @Test
    void shouldGetBookByInfoByName(){
        given()
                .queryParam("name", "The Heart Is a Lonely Hunter")
                .when()
                .get("/search/getByInfo")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("The Heart Is a Lonely Hunter"));
    }

    @Test
    void shouldGetBookByInfoByNameAndAutor(){
        given()
                .queryParams("name", "The Heart Is a Lonely Hunter", "author", "Sonja Hammes")
                .when()
                .get("/search/getByInfo")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("The Heart Is a Lonely Hunter"));
    }

    //negative test
    @Test
    void shouldGetBookByInfoByAutor(){
        given()
                .queryParam( "author", "Sonja Hammes")
                .when()
                .get("/search/getByInfo")
                .then()
                .log().all()
                .statusCode(400)
                .body("author", equalTo("Sonja Hammes"));
    }
}
