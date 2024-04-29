package homeworks.day02;

import base_urls.HomeworkBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class HW05 extends HomeworkBaseUrl {
      /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then
            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */
    @Test
    public void hw05Test(){
        // 1- Set the url
        spec.pathParam("first","unknown");

        // 2- Send the request and get the response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();

        // 3- Status code is 200
        response.then().statusCode(200);

        // 4- Print all pantone_values
        JsonPath json = response.jsonPath();
        List<String> pantoneValues = json.getList("data.pantone_value");
        System.out.println("pantone Values = " + pantoneValues);

        // 5- Print all ids greater than 3 on the console
        List<Objects> ids = json.getList("data.findAll{it.id>3}.id");
        System.out.println("ids = " + ids);

        // 6- Assert that there are 3 ids greater than 3
        assertEquals(3,ids.size());

        // 7- Print all names whose ids are less than 3 on the console
        List<Objects> names = json.getList("data.findAll{it.id<3}.name");
        System.out.println("names = " + names);

        // 8- Assert that the number of names whose ids are less than 3 is 2
        assertEquals(2,names.size());
    }
}
