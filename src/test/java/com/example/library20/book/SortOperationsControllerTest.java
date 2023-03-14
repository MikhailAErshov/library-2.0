package com.example.library20.book;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class SortOperationsControllerTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8090";
    }

    void shouldGetNewspapersByDate(){
        given()
                .queryParam("name", "The Heart Is a Lonely Hunter")
                .when()
                .get("/sort/byDate")
                .then()
                .log().all()
                .statusCode(200);
    }

}
