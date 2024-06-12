package br.com.ada.reservala.ClientController;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PUT_Client {
    String endpoint = "http://localhost:8080/client";
    String idClient = "/11";
    @Test
    void ClientUpdate(){

        given()
                .log().all()
                .contentType("application/json")
                .body("{\"name\":\"Client 20\",\"age\":11}}")
                .when()
                .put(endpoint+idClient)
                .then()
                .log().all();
    }
}
