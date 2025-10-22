package complexjsonparse;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ComplexJsonParsingTest {

    @Test
    public void complexJsonParse() {

        try {
            Path path = Paths.get("src", "test", "resources", "complexJson.json");
            String jsonAsString = Files.readString(path);

            JsonPath jsonPath = new JsonPath(jsonAsString);

            // total courses
            int count = jsonPath.getInt("courses.size()");
            System.out.println("Total number of courses: " + count);

            // total amount
            int totalAmount = jsonPath.getInt("dashboard.purchaseAmount");
            System.out.println("Total amount is: " + totalAmount);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
