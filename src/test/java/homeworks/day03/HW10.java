package homeworks.day03;

import base_urls.PetStoreBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.AssertJUnit.assertTrue;

public class HW10 extends PetStoreBaseUrl {
    /*
    Using the https://petstore.swagger.io/ document, write an automation test
    that finds the number of "pets" with the status "available"
    and asserts that there are more than 100
     */
    @Test
    public void hw10Test(){
        spec.pathParams("first","pet"
                        ,"second","findByStatus")
                .queryParam("status","available");

        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        JsonPath json = response.jsonPath();
        List<Integer> idsOfAvailablePets = json.getList("id");
        System.out.println("idsOfAvailablePets.size() = " + idsOfAvailablePets.size());

        // 2 ways of assertion
        assertTrue(idsOfAvailablePets.size()>100);
        response.then().body("status",hasSize(greaterThan(100)));
    }
}
