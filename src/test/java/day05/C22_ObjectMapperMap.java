package day05;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testdata.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C22_ObjectMapperMap extends JsonPlaceHolderBaseUrl {
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
        // Set url
        spec.pathParam("first","todos");

        // Set Expected Data
        //Map<String,Object> payLoad = JsonPlaceHolderTestData.jsonPlaceHolderMapper(55,"Tidy your room",false);
        String expectdStr = """
               {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
                 }""";

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> payLoad = objectMapper.readValue(expectdStr,Map.class);

        // Send request and get response
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        // Do Assertion
        Map<String,Object> actualData = objectMapper.readValue(response.asString(),Map.class);
        assertEquals(response.statusCode(),201);
        assertEquals(actualData.get("userId"),payLoad.get("userId"));
        assertEquals(actualData.get("title"),payLoad.get("title"));
        assertEquals(actualData.get("completed"),payLoad.get("completed"));
    }

}
