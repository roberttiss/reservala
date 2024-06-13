package br.com.ada.reservala.ReservationController;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class GET_All_Reservetion_By_clientId {


    String Endpoint = "http://localhost:8080/reservation";
    String ParamsEndpoint = "/";
     int ClientId = 6;
    int idReservation = 1;
    @Test
    void GetAllReservationWithID(){

            given()
                    .when().get(Endpoint + ParamsEndpoint+idReservation+ParamsEndpoint+ClientId)
                    .then()
                    .log().all()
                    .assertThat()
                    .statusCode(200);
        }

    }

