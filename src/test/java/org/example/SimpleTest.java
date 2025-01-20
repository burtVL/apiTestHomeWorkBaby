package org.example;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;


public class SimpleTest {
    @BeforeAll
    public static void setupTests() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = "https://crudcrud.com/api/8623c44dfe5d4ad5a6a42330ff072a93";
    }

    @Test
    public void userShouldBeAbleCreateUnicorn() {
        //given-when-than BDD
        given()
                .body("{\n" +
                        "    \"name\": \"Donald\",\n" +
                        "    \"tail\": \"white\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/unicorns")
                .then()
                .assertThat()
                .statusCode(201)
                .body("$", hasKey("_id"));
    }

    @Test
    public void userShouldBeAbleChangeTailColor() {
        String id = given()
                .body("{\n" +
                        "    \"name\": \"Snow\",\n" +
                        "    \"tail\": \"black\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/unicorns")
                .then()
                .assertThat()
                .statusCode(201)
                .body("$", hasKey("_id"))
                .extract()
                .path("_id");

        //Шаг 2. Изменение хвоста цвета единорога

        given()
                .body("{\n" +
                        "    \"tail\": \"pink\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .put("/unicorns/" + id)
                .then()
                .assertThat()
                .statusCode(200);

        // Шаг 3. Проверить, что цвет хвоста единорога изменился

        given()
                .when()
                .get("/unicorns/" + id)
                .then()
                .assertThat()
                .statusCode(200)
                .body("tail", equalTo("pink"));
    }

    @Test
    public void userShouldBeAbleDeleteExistingUnicorn() {
        // Атомарность
        // Тест должен сам себя готовить


        //Шаг 1. Создание единорога
        String id = given()
                .body("{\n" +
                        "    \"name\": \"Romeo\",\n" +
                        "    \"tail\": \"red\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/unicorns")
                .then()
                .assertThat()
                .statusCode(201)
                .body("$", hasKey("_id"))
                .extract()
                .path("_id");

        //Шаг 2. Удаление единорога

        given().delete("/unicorns/" + id)
                .then()
                .assertThat()
                .statusCode(200);

        // Шаг 3. Проверить, что единорога болше не существует

        given()
                .get("/unicorns/" + id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
