package day05;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C23_ObjectMapperPojo extends JsonPlaceHolderBaseUrl {
    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
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

Note: Use map and POJO seperately
*/
    @Test
    public void test() throws JsonProcessingException {

        spec.pathParam("first","todos");

        //JsonPlaceHolderPojo payLoad = new JsonPlaceHolderPojo(55,"Tidy your room",false);
        String expectdStr = """
               {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
                 }""";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonPlaceHolderPojo payLoad = objectMapper.readValue(expectdStr,JsonPlaceHolderPojo.class);

        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        JsonPlaceHolderPojo actualData = objectMapper.readValue(response.asString(),JsonPlaceHolderPojo.class);

        assertEquals(response.statusCode(),201);
        assertEquals(actualData.getUserId(),payLoad.getUserId());
        assertEquals(actualData.getTitle(),payLoad.getTitle());
        assertEquals(actualData.getCompleted(),payLoad.getCompleted());

    }
}
