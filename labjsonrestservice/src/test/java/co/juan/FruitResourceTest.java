package co.juan;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
@TestHTTPEndpoint(FruitResource.class)
public class FruitResourceTest {

    @Test
    public void testGetStatus(){
        given()
                .when().get()
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetBody(){
        given()
                .when().get()
                .then()
                .body(
                        containsString("\"name\":\"Apple\",\"description\":\"Winter fruit\""),
                        containsString("\"name\":\"Pineapple\",\"description\":\"Tropical fruit\""));

    }

    @Test
    public void testPost(){
        given()
                .body("{\"name\": \"Grape\", \"description\": \"The little one\"}")
                .header("Content-Type", "application/json")
                .when()
                .post()
                .then()
                .statusCode(200);

        given()
                .when().get()
                .then()
                .body("$.size()", is(3),
                        "[2].name", is("Grape"));
    }

    @Test
    public void testDelete(){
        given()
                .body("{\"name\": \"Apple\", \"description\": \"Winter fruit\"}")
                .header("Content-Type", "application/json")
                .when()
                .delete()
                .then()
                .statusCode(200);

        given()
                .when().get()
                .then()
                .body("$.size()", is(2),
                        "[1].name", is("Grape"));
    }

    @Inject
    FruitService service;
    @Test
    public void testService(){

        int fruitsNumber = service.getFruits().size();
        assertEquals(fruitsNumber,2);
    }
}