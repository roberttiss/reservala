package br.com.ada.reservala.ReservationController;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Equals;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class DELETE_Reservation {
    String Endpoint = "http://localhost:8080/reservation";
    String IdReservation = "/1";

    @Test
    void roomRemove(){
        /*RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.DELETE,Endpoint+IdReservation);
        String responseBodyAsString = response.getBody().asString();
        Assertions.assertFalse(responseBodyAsString.contains("Reservation with id 1 not exist."));*/

        given().log()
                .all()
                .when()
                .delete(Endpoint+IdReservation)
                .then().assertThat().statusCode(204);
    }



}
