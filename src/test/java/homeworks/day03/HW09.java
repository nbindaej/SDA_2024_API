package homeworks.day03;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.PetStoreTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HW09 extends PetStoreBaseUrl {
    /*
Write an automation test that will create a 'user' using the "https://petstore.swagger.io/" document
*/
    @Test
    public void hw09Test(){
        spec.pathParam("first","user");
        Map<String,Object> payLoad = PetStoreTestData.PetStoreMapper("noraziz"
                                       ,"Norah"
                                       ,"Aziz"
                                       ,"norah@email.com"
                                       ,"abcd1234"
                                       ,0501122334);

        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        Map<String,Object> actualData = response.as(Map.class);

        assertEquals(response.statusCode(),200);
        assertEquals(actualData.get("code"),200);
        assertEquals(actualData.get("type"),"unknown");
    }
}
