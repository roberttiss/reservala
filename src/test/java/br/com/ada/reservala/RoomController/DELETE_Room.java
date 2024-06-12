package br.com.ada.reservala.RoomController;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DELETE_Room {
    String Endpoint = "http://localhost:8080/room";
    String ParamsEndpoint = "/";
    int id = 101;

    @Test
    void roomRemove(){
        given().log()
                .all()
                .when()
                .delete(Endpoint+ParamsEndpoint+id)
                .then().statusCode(204);
    }

    @Test
    void roomInexistRemove(){

        id = 1000;
        String error = "Room with number 101 not found.";
        given().log()
                .all()
                .when()
                .delete(Endpoint+ParamsEndpoint+id)
                .then().statusCode(404).assertThat().log().body();

    }
}
