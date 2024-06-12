package br.com.ada.reservala.ReservationController;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class POST_Client {
    String Endpoint = "http://localhost:8080/client";
    @Test
    void CreateRoom(){
        given()
                .log().all()
                .contentType("application/json")
                .body("\"name\":\"Client 20\",\"age\":30")
                .when()
                .post(Endpoint)
                .then()
                .log().all();
    }
}
