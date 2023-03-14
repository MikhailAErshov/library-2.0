package com.example.library20.book;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.library20.Application.books;
import static com.example.library20.utils.GenerateUtils.generateSomeBooks;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class BookControllerTest {

    private final Faker faker = new Faker();
    private RequestSpecification request;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8090";
    }

    @BeforeEach
    public void setClient() {
        request = given();
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

        response
                .then()
                .statusCode(200)
                .assertThat().body("name", equalTo(book.getName()),
                        "author", equalTo(book.getAuthor()));

//        response.then().body("name", equalTo(book.getName()),
//                "author", equalTo(book.getAuthor())).log().body();
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

    @Test
    public void shouldGetBooksTestForFiveBook() {

        when()
                .get("/book/get/{index}", 1)
                .then()
                .body("name", equalTo("I Know Why the Caged Bird Sings"))
                .log()
                .body();
    }

    @Test
    public void shouldGetBooksTestF() {
        Response response =
                when()
                        .get("/book/get/{index}", 1)
                        .then()
                        .extract()
                        .response();

        String jsonGetString = response.asString();
        System.out.println(jsonGetString);

        Headers header = response.getHeaders();
        System.out.println(header);
    }

    @Test
    public void test() {
        List<Books> persons =
                given()
                        .when()
                        .get("/book/get/{index}", 1)
                        .then()
                        .extract()
                        .body()
                        // here's the magic
                        .jsonPath().getList(".", Books.class);
    }

    @Test
    public void clearTest() {

        when()
                .delete("/book/clear")
                .then()
                .statusCode(204);

        final BookDto book = new BookDto(faker.book().title(), faker.book().author());

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", book.getName());
        requestParams.put("author", book.getAuthor());

        given().
                contentType(ContentType.JSON).
                body(requestParams.toString()).
                when().
                post("/book/add_target").
                then().log().all().statusCode(200);

        when()
                .get("/book/get/{index}", 0)
                .then()
                .statusCode(200)
                .body("name", equalTo(book.getName()),
                        "author", equalTo(book.getAuthor()));
    }
}
