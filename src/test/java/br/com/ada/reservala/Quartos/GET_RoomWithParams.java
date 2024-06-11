package br.com.ada.reservala.Quartos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
@SpringBootTest
public class GET_RoomWithParams {
    String Endpoint = "http://localhost:8080/room";
    String ParamsEndpoint = "?roomNumber=";
     int id = 11;

    @Test
    void GetRoom (){
        given()
                .when()
                .get(Endpoint + ParamsEndpoint+id)
                .getBody().prettyPrint();
        }


}
