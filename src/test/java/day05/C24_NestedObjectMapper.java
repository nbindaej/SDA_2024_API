package day05;

import base_urls.RestFullBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;

import java.awt.print.Book;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C24_NestedObjectMapper extends RestFullBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/466
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
                "firstname": "Jane",
                "lastname": "Doe",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Extra pillows please"
            }
*/
    @Test
    public void test() throws JsonProcessingException {
        spec.pathParams("first","booking","second",15);

        String expectedStr = """
                {
                                "firstname": "Jane",
                                "lastname": "Doe",
                                "totalprice": 111,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2018-01-01",
                                    "checkout": "2019-01-01"
                                },
                                "additionalneeds": "Extra pillows please"
                            }
                """;

        ObjectMapper objectMapper = new ObjectMapper();
        BookingPojo expectedData = objectMapper.readValue(expectedStr, BookingPojo.class);

        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
        BookingPojo actualData = objectMapper.readValue(response.asString(), BookingPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(actualData.getFirstname(),expectedData.getFirstname());
        assertEquals(actualData.getLastname(),expectedData.getLastname());
        assertEquals(actualData.getTotalprice(),expectedData.getTotalprice());
        assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid());
        assertEquals(actualData.getBookingdates().getCheckin(),expectedData.getBookingdates().getCheckin());
        assertEquals(actualData.getBookingdates().getCheckout(),expectedData.getBookingdates().getCheckout());
        assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds());
    }
}
