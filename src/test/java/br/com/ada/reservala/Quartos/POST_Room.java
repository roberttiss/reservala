package br.com.ada.reservala.Quartos;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class POST_Room {
    String Endpoint = "http://localhost:8080/room";
    @Test
    void CreateRoom(){
        given()
                .log().all()
                .contentType("application/json")
                .body("{\"roomNumber\": \"12\",\"type\":\"Solteiro\",\"price\":400,\"available\":true}")
                .when()
                    .post(Endpoint)
                .then()
                .log().all().statusCode(200);
    }
}
