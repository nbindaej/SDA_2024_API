package day04;

import base_urls.RestFullBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testdata.RestFullTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static testdata.RestFullTestData.bookingdatesMapper;

public class C18_GetRequestNestedMap extends RestFullBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/243
    When
        I send GET Request to the url
    Then
        Status code should be 200
        Response body should be like that;
            {
                "firstname": "John",
                "lastname": "Smith",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
 */
    @Test
    public void getNestedMapTest(){
        // Set Url
        spec.pathParams("first","booking","second",451);

        // Set expected data
        Map<String,Object> bookingDates = RestFullTestData.bookingdatesMapper("2018-01-01","2019-01-01");
        Map<String,Object> expectedData = RestFullTestData.bookingMapper("John"
                                                                        ,"Smith"
                                                                        ,111
                                                                        ,true
                                                                        ,bookingDates
                                                                        ,"Breakfast");

        // Send a get request
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        JsonPath json = response.jsonPath();
        assertEquals(expectedData.get("firstname"),json.getString("firstname"));

        Map<String,Object> actualData = response.as(Map.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(bookingDates.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingDates.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));

    }
}
