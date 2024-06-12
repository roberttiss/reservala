package br.com.ada.reservala.ReservationController;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToObject;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToObject;
import static org.hamcrest.Matchers.is;

public class GET_All_Reservetion_By_clientId {


    String Endpoint = "http://localhost:8080/reservation";
    String ParamsEndpoint = "/";
     int ClientId = 5;
    int idReservation = 1;
    @Test
    void GetAllReservationWithID(){

            given()
                    .when().get(Endpoint + ParamsEndpoint+ClientId+ParamsEndpoint+idReservation)
                    .then().log().all()
                    .assertThat().statusCode(200);
        }

    }

