package day05;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class C26_RevisionExample01 extends JsonPlaceHolderBaseUrl {
    /*

    Given
    https://jsonplaceholder.typicode.com/todos

            When
    I send a GET request to the Url
    And
    Accept type is “application/json”
    Then
    HTTP Status Code should be 200
    And
    There must be a todo like:
    {
        "userId": 1,
            "id": 4,
            "title": "et porro tempora",
            "completed": true
    }
*/
     @Test
    public void test(){
         // Set Url
         spec.pathParam("first","todos");

         // Set Expected Data
         String expectedStr = """
                 {
                         "userId": 1,
                         "id": 4,
                         "title": "et porro tempora",
                         "completed": true
                     }
                 """;

         Map<String,Object> expectedData = convertJsonToJava(expectedStr,Map.class);

         // Send request and get response
         Response response = given(spec).when().get("{first}");
         //response.prettyPrint();

         JsonPath json = response.jsonPath();
         Object userId = json.getList("findAll{it.id==4}.userId").get(0);
         Object title = json.getList("findAll{it.id==4}.title").get(0);
         Object id = json.getList("findAll{it.id==4}.id").get(0);
         Object completed = json.getList("findAll{it.id==4}.completed").get(0);

         assertEquals(expectedData.get("userId"),userId);
         assertEquals(expectedData.get("title"),title);
         assertEquals(expectedData.get("id"),id);
         assertEquals(expectedData.get("completed"),completed);

         //____________________________________________________________
         // 2nd way
         Object myList = json.getList("findAll{it.id==4}").get(0);
         Map<String,Object> actualData = (Map)myList;

         assertEquals(expectedData.get("userId"),actualData.get("userId"));
         assertEquals(expectedData.get("title"),actualData.get("title"));
         assertEquals(expectedData.get("id"),actualData.get("id"));
         assertEquals(expectedData.get("completed"),actualData.get("completed"));

     }
}
