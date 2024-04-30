package day05;

import base_urls.RestFullBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utilities.ObjectMapperUtilities;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class C25_NestedPostObjectMapper extends RestFullBaseUrl {
     /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
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
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like
       {
           "bookingid": 2243,
           "booking":{
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
            }
*/
    @Test
    public void test() {
        spec.pathParam("first","booking");

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
        BookingPojo payLoad = convertJsonToJava(expectedStr, BookingPojo.class);

        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        BookingResponsePojo actualData = convertJsonToJava(response.asString(),BookingResponsePojo.class);

        assertEquals(response.statusCode(),200);
        assertEquals(actualData.getBooking().getFirstname(),payLoad.getFirstname());
        assertEquals(actualData.getBooking().getLastname(),payLoad.getLastname());
        assertEquals(actualData.getBooking().getTotalprice(),payLoad.getTotalprice());
        assertEquals(actualData.getBooking().getDepositpaid(),payLoad.getDepositpaid());
        assertEquals(actualData.getBooking().getBookingdates().getCheckin(),payLoad.getBookingdates().getCheckin());
        assertEquals(actualData.getBooking().getBookingdates().getCheckout(),payLoad.getBookingdates().getCheckout());
        assertEquals(actualData.getBooking().getAdditionalneeds(),payLoad.getAdditionalneeds());

    }
}
