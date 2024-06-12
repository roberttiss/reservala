package br.com.ada.reservala.ReservationController;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToObject;
import static org.hamcrest.Matchers.is;

public class GET_Reservation {
    String Endpoint = "http://localhost:8080/reservation";
    String ParamsEndpoint = "/";
    int idReservation = 1;


    @Test
    void reservationValidation(){
        int roomNumber = 106;
        given()
                .when().get(Endpoint + ParamsEndpoint+idReservation)
                .then().log().all()
                .body("roomNumber", is(equalToObject(roomNumber)))
                .assertThat().statusCode(200);
    }
}
