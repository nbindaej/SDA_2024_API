package day03;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;
import testdata.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C15_PostRequestPojo extends JsonPlaceHolderBaseUrl {
    /*
     Given
        https://jsonplaceholder.typicode.com/todos
        {
        "userId": 55,
        "title": "Tidy your room",
        "completed": false
        }
    When
        I send POST Request to the Url
    Then
        Status code is 201
    And
        response body is like {
                                "userId": 55,
                                "title": "Tidy your room",
                                "completed": false,
                                "id": 201
                                }
*/
    @Test
    public void PostRequestPojoTest(){
        spec.pathParam("first","todos");

        JsonPlaceHolderPojo payLoad = new JsonPlaceHolderPojo(55
                                                             ,"Tidy your room"
                                                             ,false);

        System.out.println("payLoad = " + payLoad);

        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        assertEquals(201,response.statusCode());

        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        assertEquals(payLoad.getUserId(),actualData.getUserId());
        assertEquals(payLoad.getTitle(),actualData.getTitle());
        assertEquals(payLoad.getCompleted(),actualData.getCompleted());
    }
}
