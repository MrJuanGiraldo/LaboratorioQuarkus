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
@TestHTTPEndpoint(LegumeResource.class)

public class LegumeResourceTest {

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
                        containsString("\"name\":\"Carrot\",\"description\":\"Root vegetable, usually orange\""),
                        containsString("\"name\":\"Zucchini\",\"description\":\"Summer squash\""));

    }

    @Test
    public void testPost(){
        given()
                .body("{\"name\": \"Tomatoe\", \"description\": \"The red one\"}")
                .header("Content-Type", "application/json")
                .when()
                .post()
                .then()
                .statusCode(200);

        given()
                .when().get()
                .then()
                .body("$.size()", is(3),
                        "[2].name", is("Tomatoe"));
    }

    @Test
    public void testDelete(){
        given()
                .body("{\"name\": \"Carrot\", \"description\": \"Root vegetable, usually orange\"}")
                .header("Content-Type", "application/json")
                .when()
                .delete()
                .then()
                .statusCode(200);

        given()
                .when().get()
                .then()
                .body("$.size()", is(2),
                        "[1].name", is("Tomatoe"));
    }

    @Inject
    LegumeService service;
    @Test
    public void testService(){

        int legumesNumber = service.getLegumes().size();
        assertEquals(legumesNumber,2);
    }
}