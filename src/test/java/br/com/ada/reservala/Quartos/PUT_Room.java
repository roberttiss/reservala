package br.com.ada.reservala.Quartos;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PUT_Room {
    String endpoint = "http://localhost:8080/room";
    String complement = "/12";
    @Test
    void RoomUpdate(){

            given()
                    .log().all()
                    .contentType("application/json")
                    .body("{\"roomNumber\": \"12\",\"type\":\"Solteiro\",\"price\":6700,\"avalaible\": true}")
                    .when()
                    .put(endpoint+complement)
                    .then()
                    .log().all();
        }
    }

