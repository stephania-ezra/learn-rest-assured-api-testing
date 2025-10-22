package tests;

import io.restassured.path.json.JsonPath;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class RetrievePlaceTest {

    private static final Logger logger = Logger.getLogger(RetrievePlaceTest.class.getName());

    @Test(groups = {"smoke1"}, dependsOnGroups = {"smoke"})
    void getPlace(ITestContext context) {
        String placeId = (String) context.getAttribute("placeId");

        String response = given()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when()
                .get("maps/api/place/get/json")
                .then()
                .assertThat().log().all().statusCode(200)
                .extract().response().asString();

        //parse the string and get the name alone
        logger.log(Level.INFO, "response is " + response);
        JsonPath jsonPath = new JsonPath(response);
        assertEquals(jsonPath.getString("name"), "Frontline house");
    }
}
