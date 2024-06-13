package br.com.ada.reservala.RoomController;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class POST_Room {
    String Endpoint = "http://localhost:8080/room";
    @Test
    void CreateRoom(){
        given()
                .log().all()
                .contentType("application/json")
                .body("{\"roomNumber\": \"111\",\"type\":\"Double\",\"price\":100,\"available\":true}")
                .when()
                    .post(Endpoint)
                .then()
                .log().all();
    }
}
