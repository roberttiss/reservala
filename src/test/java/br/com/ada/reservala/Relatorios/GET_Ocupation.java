package br.com.ada.reservala.Relatorios;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GET_Ocupation {
    String endpoint = "http://localhost:8080/room";
    String endPoint= "/ocupation";

    @Test
    void CheckOcupation(){
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET,endpoint+endPoint);
        String responseBodyAsString = response.getBody().asString();
        Assertions.assertFalse(responseBodyAsString.contains("0"));
    }

}
