package br.com.ada.reservala;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@SpringBootTest
class ReservalaApplicationTests {

	@Test
	void contextLoads() {
		given()
				.when().get("http://localhost:8080/room")
				.then()
				.body("roomNumber", is(hasItem(7)));
	}

}
