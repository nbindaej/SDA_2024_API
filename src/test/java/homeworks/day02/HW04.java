package homeworks.day02;

import base_urls.HomeworkBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HW04 extends HomeworkBaseUrl {
         /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json; charset=utf-8"
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */

    @Test
    public void hw04Test(){
        // 1- Set the url
        spec.pathParams("first","unknown"
                , "second",3);

        // 2- Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // 3- Assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id",equalTo(3)
                        ,"data.name",equalTo("true red")
                        ,"data.year",equalTo(2002)
                        ,"data.color",equalTo("#BF1932")
                        ,"data.pantone_value",equalTo("19-1664")
                        ,"support.url",equalTo("https://reqres.in/#support-heading")
                        ,"support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

        // Another way of Assertion
        JsonPath json = response.jsonPath();
        int id = json.getInt("data.id");
        String name = json.getString("data.name");
        int year = json.getInt("data.year");
        String color = json.getString("data.color");
        String pantoneValue = json.getString("data.pantone_value");
        String url = json.getString("support.url");
        String text = json.getString("support.text");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(id,3);
        softAssert.assertEquals(name,"true red");
        softAssert.assertEquals(year,2002);
        softAssert.assertEquals(color,"#BF1932");
        softAssert.assertEquals(pantoneValue,"19-1664");
        softAssert.assertEquals(url,"https://reqres.in/#support-heading");
        softAssert.assertEquals(text,"To keep ReqRes free, contributions towards server costs are appreciated!");


    }
}
