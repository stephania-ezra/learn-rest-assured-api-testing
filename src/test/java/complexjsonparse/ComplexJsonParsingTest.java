package complexjsonparse;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
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

            //firstCourseTitle

           String firstCourseTitle = jsonPath.getString("courses[0].title");
           System.out.println("First Course Title is:"+firstCourseTitle);

           //Print all course title

            for(int i =0 ;i< count ;i++) {
                String courseTitles = jsonPath.getString("courses[" + i + "].title");
                int coursePrice = jsonPath.getInt("courses["+i+"].price");

                System.out.println("Course Title is:"+courseTitles);
                System.out.println("Course Price is:"+coursePrice);
            }

            //No.of.copies sold by RPA course

            for (int i =0 ;i < count ; i++){
                String courseTitles = jsonPath.getString("courses[" + i + "].title");
                if(courseTitles.equalsIgnoreCase("RPA")){
                    int copies= jsonPath.getInt("courses["+i+"].copies");
                    System.out.println("No.of copies sold by RPA course is:"+copies);
                    break;
                }
            }


            //purchase amount calculation
            int sum=0;
           for(int i =0 ;i <count ;i++){
               int coursePrice = jsonPath.getInt("courses["+i+"].price");
               int courseCopies = jsonPath.getInt("courses["+i+"].copies");
               int eachCoursePrice = coursePrice * courseCopies;
               sum = sum + eachCoursePrice;
           }
            System.out.println("sum is:"+sum);
           Assert.assertEquals(sum,totalAmount);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
