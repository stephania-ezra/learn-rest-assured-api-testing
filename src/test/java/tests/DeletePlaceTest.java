package tests;

import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertNotNull;

public class DeletePlaceTest {

    private static final Logger logger = Logger.getLogger(DeletePlaceTest.class.getName());

    @BeforeTest
    public static void verifyConfig(ITestContext context) {
        assertNotNull(context.getAttribute("placeId"));
    }

    @Test(groups = {"smoke3"}, dependsOnGroups = {"smoke"})
    public void deletePlace(ITestContext context) {
        String placeId = context.getAttribute("placeId").toString();
        logger.log(Level.INFO, "ITestContext " + placeId);

        try {
            Path path = Paths.get("src", "test", "resources", "deletePlace.json");
            String body = Files.readString(path);
            logger.log(Level.INFO, "body is " + body);
            body = body.replaceAll("REPLACE_PLACE_ID", placeId);

            given()
                    .queryParam("key", "qaclick123")
                    .header("Content-type", "application/json")
                    .body(body)
                    .when().put("maps/api/place/delete/json")
                    .then().assertThat().log().all().statusCode(200)
                    .body("status",
                            equalTo("OK"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


