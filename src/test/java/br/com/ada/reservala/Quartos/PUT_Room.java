package br.com.ada.reservala.Quartos;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PUT_Room {
    String endpoint = "http://localhost:8080/room";
    String complement = "?roomNumber=11";
    @Test
    void RoomUpdate(){

            given()
                    .log().all()
                    .contentType("application/json")
                    .body("{\"roomNumber\": \"21\",\"type\":\"Solteiro\",\"price\":500,\"available\":true}")
                    .when()
                    .put("http://localhost:8080/room?roomNumber=11")
                    .then()
                    .log().all();
        }
    }

