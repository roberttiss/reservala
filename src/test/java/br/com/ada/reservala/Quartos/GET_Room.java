package br.com.ada.reservala.Quartos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToObject;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class GET_Room {
    String Endpoint = "http://localhost:8080/room";

    //Testes End Point /room post
    @Test
    void numberRoomValidation() {
        //List<Integer> roomNumber = Collections.singletonList(11);
        List<Integer> roomNumber = new ArrayList<>();
        roomNumber.add(11);
        roomNumber.add(12);
        roomNumber.add(13);
        roomNumber.add(14);


        given()
                .when().get(Endpoint)
                .then()
                .body("roomNumber", is(equalToObject(roomNumber)))
                .assertThat().statusCode(200);
    }
    @Test
    void typeValidation() {

        //List<String> roomtype = Collections.singletonList("Solteiro");
        List<String> roomtype = new ArrayList<>();
        roomtype.add("Solteiro");
        roomtype.add("Solteiro");
        roomtype.add("Solteiro");
        roomtype.add("Solteiro");

        given()
                .when().get("http://localhost:8080/room")
                .then()
                .body("type", is(equalToObject(roomtype)))
                .assertThat().statusCode(200);
    }
    @Test
    void priceValidation() {
       // List<Integer> roomPrice = Collections.singletonList(400);

        List<Integer> roomPrice = new ArrayList<>();
        roomPrice.add(400);
        roomPrice.add(400);
        roomPrice.add(400);
        roomPrice.add(400);


        given()
                .when().get("http://localhost:8080/room")
                .then()
                .body("price", is(equalToObject(roomPrice)))
                .assertThat().statusCode(200);
    }
    @Test
    void avaliableValidation() {

        List<Boolean> roomAvaliable = new ArrayList<>();
        roomAvaliable.add(false);
        roomAvaliable.add(false);
        roomAvaliable.add(false);
        roomAvaliable.add(false);

        given()
                .when().get("http://localhost:8080/room")
                .then()
                .body("available", is(equalToObject(roomAvaliable)))
                .assertThat().statusCode(200);
    }
    @Test
    void numberRoomValidationn() {
        //List<Integer> roomNumber = Collections.singletonList(7);
        List<Integer> roomNumber = new ArrayList<>();
        roomNumber.add(11);
        roomNumber.add(12);
        roomNumber.add(13);
        roomNumber.add(14);

        given()
                .queryParam("roomNumber", "7")
                .when().get("http://localhost:8080/room")
                .then()
                .body("roomNumber", is(equalToObject(roomNumber)))
                .assertThat().statusCode(200);
    }
}
