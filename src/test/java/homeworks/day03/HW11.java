package homeworks.day03;

import base_urls.AutomationExBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class HW11 extends AutomationExBaseUrl {
    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET request
    Then
        Assert that the number of "Women" user type is 12
    Note: Print using JsonPath: response.jsonPath().prettyPrint();
*/

    @Test
    public void hw11Test() {
        spec.pathParam("first","productsList");

       Response response = given(spec).when().get("{first}");
       response.jsonPath().prettyPrint();

       JsonPath json = response.jsonPath();
       List<String> userType = json.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
       System.out.println("userType = " + userType);
       assertEquals(userType.size(),12);

    }
}
