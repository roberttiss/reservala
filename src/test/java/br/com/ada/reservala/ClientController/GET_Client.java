package br.com.ada.reservala.ClientController;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GET_Client {
    String Endpoint = "http://localhost:8080/client";

    @Test
    void nameValidation(){
        List<String> name = new ArrayList<>();
        name.add("Client 1");
        name.add("Client 2");
        name.add("Client 3");
        name.add("Client 4");
        name.add("Client 5");
        name.add("Client 6");
        name.add("Client 7");
        name.add("Client 8");
        name.add("Client 9");
        name.add("Client 10");
        name.add("Client 22");



        given()
                .when()
                .get(Endpoint)
                .then()
                .body("name", is(equalToObject(name)))
                .assertThat().statusCode(200);
    }

    @Test
    void ageValidation(){
        List<Integer> age = new ArrayList<>();
        age.add(30);
        age.add(35);
        age.add(40);
        age.add(45);
        age.add(50);
        age.add(55);
        age.add(60);
        age.add(65);
        age.add(70);
        age.add(75);
        age.add(30);



        given()
                .when().get(Endpoint)
                .then()
                .body("age", is(equalToObject(age)))
                .assertThat().statusCode(200);


    }


}
