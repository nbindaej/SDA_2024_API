package homeworks.day05;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtilities;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class HW13 extends PetStoreBaseUrl {
    /*

    Write an automation test that will create a 'user' then read, update and delete
    the created user using the "https://petstore.swagger.io/" document. (Create a classes for each request.)

     */
    @Test
    public void test() throws InterruptedException {
        // Set Url
        spec.pathParam("first","user");

        // Set Expected Data
        String expectedStr = """
                {
                  "id": 0,
                  "username": "Noraziz",
                  "firstName": "Norah",
                  "lastName": "Aziz",
                  "email": "nora@email.com",
                  "password": "abcd1234",
                  "phone": "0501122334",
                  "userStatus": 0
                    }            
                """;

        Map<String,Object> payLoad = convertJsonToJava(expectedStr, Map.class);

        // 1- Send Post Request
        Response postResponse = given(spec).body(payLoad).when().post("{first}");
        postResponse.prettyPrint();

        // Post Response Assertion
        postResponse
                .then()
                .statusCode(200)
                .body("code",equalTo(200),
                        "type",equalTo("unknown"));

        Thread.sleep(2);


        //___________________________________________________________
        // 2- Send Get Request
        Response getResponse = given(spec).when().get("{first}/"+payLoad.get("username"));
        getResponse.prettyPrint();
        Map<String,Object> actualData = convertJsonToJava(getResponse.asString(),Map.class);

        // Get Response Assertion
        assertEquals(actualData.get("username"),payLoad.get("username"));
        assertEquals(actualData.get("firstName"),payLoad.get("firstName"));
        assertEquals(actualData.get("lastName"),payLoad.get("lastName"));
        assertEquals(actualData.get("email"),payLoad.get("email"));
        assertEquals(actualData.get("password"),payLoad.get("password"));
        assertEquals(actualData.get("phone"),payLoad.get("phone"));

        //__________________________________________________________
        // 3- Send Put Request
        payLoad.put("password","changed");
        payLoad.put("phone","0501111111");

        Response putResponse = given(spec).body(payLoad).when().put("{first}/"+payLoad.get("username"));
        putResponse.prettyPrint();

        // Put Request Assetion ---> assert by getting the data again
        Response getResponseToCheck = given(spec).when().get("{first}/"+payLoad.get("username"));
        getResponseToCheck.prettyPrint();

        Map<String,Object> updatedData = convertJsonToJava(getResponseToCheck.asString(),Map.class);

        assertEquals(payLoad.get("password"),updatedData.get("password"));
        assertEquals(payLoad.get("phone"),updatedData.get("phone"));

        //__________________________________________________________
        // 4- Send Delete Request
        Response deleteResponse = given(spec).when().delete("{first}/"+payLoad.get("username"));
        deleteResponse.prettyPrint();

        // Delete Response Assertion
        deleteResponse
                .then()
                .statusCode(200)
                .body("message",equalTo(payLoad.get("username")));

    }

}
