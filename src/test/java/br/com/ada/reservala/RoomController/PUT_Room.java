package br.com.ada.reservala.RoomController;

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
                    .body("{\"idReservation\":1,\"idClient\":6,\"roomNumber\":107,\"checkIn\":\"2026-12-12\",\"checkOut\":\"2027-12-10\"}")
                    .when()
                    .put(endpoint+complement)
                    .then()
                    .log().all();
        }

    }



