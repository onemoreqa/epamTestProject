package otus.les23.stubs;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static io.restassured.RestAssured.given;

@SpringBootTest
class StubsApplicationTests {

	private static WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(5050));

	@BeforeAll
	public static void setUpMockServer() {
		wireMockServer.start();
		WireMock.configureFor("localhost", 5050);
	}

	@Test
	public void deleteUserTest() {
		WireMock.stubFor(WireMock.delete(WireMock.urlEqualTo("/api/users/2"))
				.willReturn(WireMock.aResponse()
						.withStatus(204)));

		Response response = given()
				.contentType(ContentType.JSON)
				.when()
				.delete("http://localhost:5050/api/users/2")
				.then()
				.extract().response();

		Assertions.assertEquals(204, response.statusCode());
	}

	@Test
	public void getResourceTest() {
		WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/unknown/5"))
				.willReturn(WireMock.aResponse()
						.withStatus(200)
						.withBody("{\n" +
								"    \"data\": {\n" +
								"        \"id\": 5,\n" +
								"        \"name\": \"tigerlily\",\n" +
								"        \"year\": 2004,\n" +
								"        \"color\": \"#E2583E\",\n" +
								"        \"pantone_value\": \"17-1456\"\n" +
								"    },\n" +
								"    \"support\": {\n" +
								"        \"url\": \"https://reqres.in/#support-heading\",\n" +
								"        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
								"    }\n" +
								"}")));


		Response response = given()
				.contentType(ContentType.JSON)
				.when()
				.get("http://localhost:5050/api/unknown/5")
				.then()
				.extract().response();

		Assertions.assertEquals(200, response.statusCode());
		Assertions.assertEquals("tigerlily", response.jsonPath().getString("data.name"));
		Assertions.assertEquals("#E2583E", response.jsonPath().getString("data.color"));
	}

	@Test
	public void createUserTest() {
		WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/users"))
				.willReturn(WireMock.aResponse()
						.withStatus(201)
						.withBody("{\n" +
								"    \"name\": \"morpheus\",\n" +
								"    \"job\": \"leader\",\n" +
								"    \"id\": \"78\",\n" +
								"    \"createdAt\": \"2021-07-24T23:43:09.945Z\"\n" +
								"}")));

		Response response = given()
				.contentType(ContentType.JSON)
				.with()
				.body("{\n" +
						"    \"name\": \"morpheus\",\n" +
						"    \"job\": \"leader\"\n" +
						"}")
				.when()
				.post("http://localhost:5050/api/users")
				.then()
				.extract().response();

		//response.prettyPrint();

		Assertions.assertEquals(201, response.statusCode());
		Assertions.assertEquals("morpheus", response.jsonPath().getString("name"));
		Assertions.assertEquals("78", response.jsonPath().getString("id"));
	}

	@AfterAll
	public static void tearDownMockServer() throws InterruptedException {
		//Thread.sleep(10000); //<-- add for check stubs via postman/web
		wireMockServer.stop();
	}
}
