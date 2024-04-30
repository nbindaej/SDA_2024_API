package homeworks.day06;

import base_urls.RestFullBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingResponsePojo;
import pojos.restfulbooker.BookerResponse;
import pojos.restfulbooker.Booking;
import utilities.ObjectMapperUtilities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class HW14 extends RestFullBaseUrl {

    Booking payLoad;
    BookerResponse actualData;
    @Test
    public void createBookingTest(){
        // 1- Set Url
        spec.pathParam("first","booking");

        // 2- Set Expected Data
        String expectedStr = """
                {
                    "firstname" : "Norah",
                    "lastname" : "Aziz",
                    "totalprice" : 500,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2024-04-30",
                        "checkout" : "2024-05-10"
                    },
                    "additionalneeds" : "Dinner"
                }
                """;
        payLoad = convertJsonToJava(expectedStr, Booking.class);

        // 3- Send Request and get Response
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();
        actualData = convertJsonToJava(response.asString(),BookerResponse.class);

        // 4- Do Assertion
        assertEquals(response.statusCode(),200);
        assertEquals(actualData.getBooking().getFirstname(),payLoad.getFirstname());
        assertEquals(actualData.getBooking().getLastname(),payLoad.getLastname());
        assertEquals(actualData.getBooking().getTotalprice(),payLoad.getTotalprice());
        assertEquals(actualData.getBooking().isDepositpaid(),payLoad.isDepositpaid());
        assertEquals(actualData.getBooking().getBookingdates().getCheckin(),payLoad.getBookingdates().getCheckin());
        assertEquals(actualData.getBooking().getBookingdates().getCheckout(),payLoad.getBookingdates().getCheckout());
        assertEquals(actualData.getBooking().getAdditionalneeds(),payLoad.getAdditionalneeds());
    }

    @Test(dependsOnMethods = "createBookingTest")
    public void getBookingTest(){
        // 1- Set Url
        spec.pathParams("first","booking","second",actualData.getBookingid());

        // 2- Send Request and get Response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // 3- Do Assertion
        response
                .then()
                .statusCode(200)
                .body("firstname",equalTo(payLoad.getFirstname()),
                        "lastname",equalTo(payLoad.getLastname()),
                        "totalprice",equalTo(payLoad.getTotalprice()),
                        "depositpaid",equalTo(payLoad.isDepositpaid()),
                        "bookingdates.checkin",equalTo(payLoad.getBookingdates().getCheckin()),
                        "bookingdates.checkout",equalTo(payLoad.getBookingdates().getCheckout()),
                        "additionalneeds",equalTo(payLoad.getAdditionalneeds()));
    }
}
