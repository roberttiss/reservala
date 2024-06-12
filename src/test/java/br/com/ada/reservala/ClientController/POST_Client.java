package br.com.ada.reservala.ClientController;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class POST_Client {
    String Endpoint = "http://localhost:8080/client";
    @Test
    void CreateClient(){
        given()
                .log().all()
                .contentType("application/json")
                .body("{\"name\":\"Client 22\",\"age\":30}}")
                .when()
                .post(Endpoint)
                .then()
                .log().all();
    }
}
