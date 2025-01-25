package org.example.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.example.api.models.Unicorn;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class UnicornRequests {

    public static Unicorn createUnicorn(Unicorn unicorn) {
        ObjectMapper objectMapper = new ObjectMapper();
        String unicornJson = null;
        try {
            unicornJson = objectMapper.writeValueAsString(unicorn);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return given()
                .body(unicornJson)
                .contentType(ContentType.JSON)
                .when()
                .post("/unicorns")
                .then()
                .assertThat()
                .statusCode(201)
                .body("$", hasKey("_id"))
                .extract().as(Unicorn.class, ObjectMapperType.GSON);

    }

    public static void deleteUnicorn(String id) {
        given().delete("/unicorns/" + id)
                .then()
                .assertThat()
                .statusCode(200);
    }
}
