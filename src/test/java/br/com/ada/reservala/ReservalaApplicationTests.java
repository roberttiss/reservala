package br.com.ada.reservala;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToObject;
import static org.hamcrest.Matchers.is;


@SpringBootTest
class ReservalaApplicationTests {



	@Test
	void numberRoomValidation() {
		int roomNumber = 0;

		given()
				.when().get("http://localhost:8080/room")
				.then()
				.body("roomNumber", is(equalToObject(roomNumber)))
				.assertThat().statusCode(200);
	}

	@Test
	void typeValidation() {
		String roomtype = "Solteiro";

		given()
				.when().get("http://localhost:8080/room")
				.then()
				.body("type", is(equalToObject(roomtype)))
				.assertThat().statusCode(200);
	}

	@Test
	void priceValidation() {
		int  roomPrice = 500;

		given()
				.when().get("http://localhost:8080/room")
				.then()
				.body("price", is(equalToObject(roomPrice)))
				.assertThat().statusCode(200);
	}

	@Test
	void avaliableValidation() {
		boolean  roomAvaliable = true;

		given()
				.when().get("http://localhost:8080/room")
				.then()
				.body("avaliable", is(equalToObject(roomAvaliable)))
				.assertThat().statusCode(200);
	}

}
