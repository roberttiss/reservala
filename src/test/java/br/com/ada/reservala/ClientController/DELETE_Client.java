package br.com.ada.reservala.ClientController;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DELETE_Client {
    String Endpoint = "http://localhost:8080/client";
    String ParamsEndpoint = "/";
    int id = 11;

    @Test
    void clientRemove(){
       given().log()
               .all()
               .when()
               .delete(Endpoint+ParamsEndpoint+id)
               .then().statusCode(204);
    }

    @Test
    void ClientInexistRemove(){

        id = 1000;
        String error = "Client with id 11 not found.";
        given().log()
                .all()
                .when()
                .delete(Endpoint+ParamsEndpoint+id)
                .then().statusCode(404).assertThat().log().body();

    }

}
