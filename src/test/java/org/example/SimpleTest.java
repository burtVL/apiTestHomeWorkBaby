package org.example;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.example.api.UnicornRequests;
import org.example.api.models.Unicorn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;


public class SimpleTest {
    @BeforeAll
    public static void setupTests() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = "https://crudcrud.com/api/830338ae9d0c4f608884cc471b8ee326";
    }

    @Test
    public void userShouldBeAbleCreateUnicorn() {
        //given-when-than BDD
        // Серилизация из json в объект и наоборот

        Unicorn unicorn = new Unicorn("Donald","white", "");

        UnicornRequests.createUnicorn(unicorn);
    }

    @Test
    public void userShouldBeAbleChangeTailColor() {

        // Шаг 1. Создание единорога

        Unicorn unicorn = new Unicorn("Snow","black", "");
        Unicorn createUnicorn = UnicornRequests.createUnicorn(unicorn);

        //Шаг 2. Изменение хвоста цвета единорога

        given()
                .body("{\n" +
                        "    \"tail\": \"pink\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .put("/unicorns/" + createUnicorn.getId())
                .then()
                .assertThat()
                .statusCode(200);

        // Шаг 3. Проверить, что цвет хвоста единорога изменился

        given()
                .when()
                .get("/unicorns/" + createUnicorn.getId())
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

        Unicorn unicorn = new Unicorn("Romeo","red", "");
        Unicorn createdUnicorn = UnicornRequests.createUnicorn(unicorn);

        //Шаг 2. Удаление единорога

        UnicornRequests.deleteUnicorn(createdUnicorn.getId());

        // Шаг 3. Проверить, что единорога болше не существует

        given()
                .get("/unicorns/" + createdUnicorn.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
