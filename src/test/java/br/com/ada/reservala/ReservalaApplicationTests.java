package br.com.ada.reservala;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToObject;
import static org.hamcrest.Matchers.is;


@SpringBootTest
class ReservalaApplicationTests {


 //Testes End Point /room post
	@Test
	void numberRoomValidation() {
		List<Integer> roomNumber = Collections.singletonList(7);

		given()
				.when().get("http://localhost:8080/room")
				.then()
				.body("roomNumber", is(equalToObject(roomNumber)))
				.assertThat().statusCode(200);
	}

	@Test
	void typeValidation() {

		List<String> roomtype = Collections.singletonList("Solteiro");
		given()
				.when().get("http://localhost:8080/room")
				.then()
				.body("type", is(equalToObject(roomtype)))
				.assertThat().statusCode(200);
	}

	@Test
	void priceValidation() {
		List<Integer> roomPrice = Collections.singletonList(500);

		given()
				.when().get("http://localhost:8080/room")
				.then()
				.body("price", is(equalToObject(roomPrice)))
				.assertThat().statusCode(200);
	}

	@Test
	void avaliableValidation() {
		List<Boolean> roomPrice = Collections.singletonList(true);

		given()
				.when().get("http://localhost:8080/room")
				.then()
				.body("avaliable", is(equalToObject(roomPrice)))
				.assertThat().statusCode(200);
	}

	//Testes End Point /room post
	@Test
	void numberRoomValidationn() {
		List<Integer> roomNumber = Collections.singletonList(7);


		given()
				.queryParam("roomNumber", "7")
				.when().get("http://localhost:8080/room")
				.then()
				.body("roomNumber", is(equalToObject(roomNumber)))
				.assertThat().statusCode(200);
	}
}
