package homeworks.day03;

import base_urls.HomeworkBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testdata.HomeworkTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static testdata.HomeworkTestData.hwMap;

public class HW08 extends HomeworkBaseUrl {

    /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */
    @Test
    public void hw08Test(){
      spec.pathParam("first","users");

      Map<String,String> payLoad= hwMap("morpheus","leader");

      Response response = given(spec).body(payLoad).when().post("{first}");
      response.prettyPrint();

      Map<String,String> actualData = response.as(Map.class);

      assertEquals(201,response.statusCode());
      assertEquals(payLoad.get("name"),actualData.get("name"));
      assertEquals(payLoad.get("job"),actualData.get("job"));

    }

}
