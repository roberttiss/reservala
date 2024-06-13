package br.com.ada.reservala.ReservationController;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PUT_Reservation {
    String Endpoint = "http://localhost:8080/reservation";
    String idReservation = "/1";

    @Test
     void atualizationReservation(){
        given()
                .log().all()
                .contentType("application/json")
                .body("{\"roomNumber\": \"12\",\"type\":\"Solteiro\",\"price\":6700,\"avalaible\": true}")
                .when()
                .put(Endpoint+idReservation)
                .then()
                .log().all();
    }
    @Test
    void checkInUpdate(){

        given()
                .log().all()
                .contentType("application/json")
                .body("{\"idReservation\":1,\"idClient\":6,\"roomNumber\":107,\"checkIn\":\"2026-12-14\",\"checkOut\":\"2027-12-10\"}")
                .when()
                .put(Endpoint+idReservation)
                .then()
                .log().all();
    }

    @Test
    void checkOutUpdate(){

        given()
                .log().all()
                .contentType("application/json")
                .body("{\"idReservation\":1,\"idClient\":6,\"roomNumber\":107,\"checkIn\":\"2026-12-14\",\"checkOut\":\"2027-12-28\"}")
                .when()
                .put(Endpoint+idReservation)
                .then()
                .log().all();
    }


}
