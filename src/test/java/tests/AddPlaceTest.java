package tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaceTest  {

    private static final Logger logger = Logger.getLogger(AddPlaceTest.class.getName());
    
    @Test(groups = {"smoke"})
    public void addPlace(ITestContext context) {

        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String placeId;
        try {
            Path path = Paths.get("src", "test", "resources", "addPlace.json");
            String body = Files.readString(path);

            String response = given()
                    .queryParam("key", "qaclick123")
                    .header("Content-type", "application/json")
                    .body(body)
                    .when()
                    .post("maps/api/place/add/json")
                    .then()
                    .assertThat().statusCode(200).body("scope", equalTo("APP"))
                    .header("server", "Apache/2.4.52 (Ubuntu)")
                    .extract().response().asString();

            //prints response in  a string format
            logger.log(Level.INFO, response);

            //parse the string and get the place_id alone
            JsonPath jsonPath = new JsonPath(response);
            placeId = (jsonPath.getString("place_id"));

            //print placeId
            context.setAttribute("placeId", placeId);
            logger.log(Level.INFO, "placeId is " + context.getAttribute("placeId"));
        } catch (IOException ie) {
            System.out.print("Error");
        }
    }
}

