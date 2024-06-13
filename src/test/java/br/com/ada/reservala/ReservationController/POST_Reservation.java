package br.com.ada.reservala.ReservationController;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class POST_Reservation {

    String Endpoint = "http://localhost:8080/reservation";

    @Test
    void CreateReservation(){
        given()
                .log().all()
                .contentType("application/json")
                .body("{\n" +
                        "    \"idClient\": 6,\n" +
                        "    \"roomNumber\": 106,\n" +
                        "    \"checkIn\": \"2026-12-12\",\n" +
                        "    \"checkOut\": \"2027-12-10\"\n" +
                        "}")
                .when()
                .post(Endpoint)
                .then()
                .log().all();
    }
}
