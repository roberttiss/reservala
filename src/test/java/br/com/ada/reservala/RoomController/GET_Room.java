package br.com.ada.reservala.RoomController;

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
        roomNumber.add(101);
        roomNumber.add(102);
        roomNumber.add(103);
        roomNumber.add(104);
        roomNumber.add(105);
        roomNumber.add(106);
        roomNumber.add(107);
        roomNumber.add(108);
        roomNumber.add(109);
        roomNumber.add(110);


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
        roomtype.add("Single");
        roomtype.add("Double");
        roomtype.add("Suite");
        roomtype.add("Single");
        roomtype.add("Double");
        roomtype.add("Suite");
        roomtype.add("Single");
        roomtype.add("Double");
        roomtype.add("Suite");
        roomtype.add("Single");

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
        roomPrice.add(100);
        roomPrice.add(200);
        roomPrice.add(300);
        roomPrice.add(100);
        roomPrice.add(200);
        roomPrice.add(300);
        roomPrice.add(100);
        roomPrice.add(200);
        roomPrice.add(300);
        roomPrice.add(100);



        given()
                .when().get("http://localhost:8080/room")
                .then()
                .body("price", is(equalToObject(roomPrice)))
                .assertThat().statusCode(200);
    }
    @Test
    void avaliableValidation() {

        List<Boolean> roomAvaliable = new ArrayList<>();
        roomAvaliable.add(true);
        roomAvaliable.add(true);
        roomAvaliable.add(true);
        roomAvaliable.add(true);
        roomAvaliable.add(true);
        roomAvaliable.add(true);
        roomAvaliable.add(true);
        roomAvaliable.add(true);
        roomAvaliable.add(true);
        roomAvaliable.add(true);




        given()
                .when().get("http://localhost:8080/room")
                .then()
                .body("available", is(equalToObject(roomAvaliable)))
                .assertThat().statusCode(200);
    }

}
