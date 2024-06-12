package br.com.ada.reservala.ClientController;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GET_ClientWithParams {

    String Endpoint = "http://localhost:8080/client";
    String ParamsEndpoint = "/";
    int id = 11;

    @Test
    void GetClient (){
        given()
                .when()
                .get(Endpoint + ParamsEndpoint+id)
                .getBody().prettyPrint();
    }
}
