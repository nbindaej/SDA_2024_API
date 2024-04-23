package day01;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.testng.AssertJUnit.*;

public class C03_HeaderAndBodyAssertion {
    /*
   Given
       https://restful-booker.herokuapp.com/booking/0
   When
       User sends a GET Request to the URL
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Response body contains "Not Found"
   And
       Response body does not contain "Clarusway"
   And
       Server is "Cowboy"
*/

    @Test
    public void bodyTest() {
//        https://restful-booker.herokuapp.com/booking/0
//        User sends a GET Request to the URL
        String url = "https://restful-booker.herokuapp.com/booking/0";
        Response response = given().when().get(url);
        response.prettyPrint();

//        HTTP Status code should be 404
//        Status Line should be HTTP/1.1 404 Not Found
//        Server is "Cowboy"

        //1st way: We can assert headers with method chain.
        response
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .header("Server","Cowboy")
                .body(containsString("Not Found"));

        //2nd way:

        assertEquals(404,response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found",response.statusLine());
        assertEquals("Cowboy",response.header("Server"));


//        Response body contains "Not Found"
        String responseString = response.asString();
        System.out.println("responseString = " + responseString);

        boolean isContain = responseString.contains("Not Found");
        boolean isEqual = responseString.equals("Not Found");
        assertTrue(isContain);
        //If the boolean value between parenthesis is TRUE test will pass

//        Response body does not contain "Clarusway"
        boolean isContainClaursway = responseString.contains("Clarusway");
        assertFalse(isContainClaursway);
        //If the boolean value between parenthesis is FALSE test will pass

    }
}
