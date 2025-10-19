## API Testing

Step 1:
    Create a Maven Project.

Step 2:
In pom.xml file under <dependecies>  , 
    Include <dependency> of "io.rest-assured" and "org.testng" from maven repository.
    version of io.rest-assured : 5.5.6
    version of org.testng :7.11.0

Step 3:
    Refer Document from "https://github.com/rest-assured/rest-assured/wiki/GettingStarted"

Step 4:
    For the contract details visit page "https://reqres.in/api-docs/"

got the error :
java.lang.IllegalStateException: Cannot serialize object because no JSON serializer found in classpath. 
Please put Jackson (Databind), Gson, Johnzon, or Yasson in the classpath.

include latest <dependency> of "com.fasterxml.jackson.core" 
                        "com.google.code.gson"
                        "org.apache.johnzon" from maven repository



	

        
