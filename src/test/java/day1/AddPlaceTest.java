package day1;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class AddPlaceTest {

    //given() - specify input details (query parameter,header,body)
    //when() - specify the http method and Resource
    //then() - validate the response like status code and body

    @Test
    public void addPlace() {

        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        try {
            Path path = Paths.get("src", "test", "resources", "addplace.json");
            String body = Files.readString(path);

            given()
                    .queryParam("key", "qaclick123")
                    .header("Content-type", "application/json")
                    .body(body)
                    .when()
                    .post("maps/api/place/add/json")
                    .then().log().all()
                    .assertThat().statusCode(200);
        } catch (IOException ie) {
            System.out.print("Error");
        }
    }
}

