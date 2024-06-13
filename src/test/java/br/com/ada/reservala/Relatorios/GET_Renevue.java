package br.com.ada.reservala.Relatorios;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class GET_Renevue {
    String endpoint = "http://localhost:8080/room";
    String endPoint= "/revenue";

    @Test
    void CheckOcupation(){
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET,endpoint+endPoint);
        String responseBodyAsString = response.getBody().asString();
        System.out.println("Movimentaçao total: "+responseBodyAsString);
    }
}
