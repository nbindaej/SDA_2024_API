package homeworks.day03;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.PetCategoryPojo;
import pojos.PetStorePojo;
import pojos.PetTagsPojo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class HW12 extends PetStoreBaseUrl {
    /*
    Write an automation test that will create, read, update, delete a 'pet'
    using the "https://petstore.swagger.io/" document
    (All actions must be done on same pet) (Use Pojo)
     */



    @Test
    public void hw12Test(){
        //Set Url
        spec.pathParam("first","pet");

        //Set expected data
        PetCategoryPojo category = new PetCategoryPojo(1,"dogs");
        List<PetTagsPojo> tags = new ArrayList<>();
        tags.add(new PetTagsPojo(25,"dogsTag"));
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("dogPhoto");

        PetStorePojo payLoad = new PetStorePojo(456
                                                  ,category
                                                  ,"snow"
                                                  ,photoUrls
                                                  ,tags
                                                  ,"available");

        //__________________________________________
        // 1- Send Post Request
        Response postResponse = given(spec).body(payLoad).when().post("{first}");
        postResponse.prettyPrint();

        // Post Response Assertion
        PetStorePojo postActualData = postResponse.as(PetStorePojo.class);
        assertEquals(postResponse.statusCode(),200);
        assertEquals(postActualData.getId(),payLoad.getId());
        //assertEquals(postActualData.getCategory(),payLoad.getCategory());
        assertEquals(postActualData.getName(),payLoad.getName());
        assertEquals(postActualData.getPhotoUrls(),payLoad.getPhotoUrls());
        //assertEquals(postActualData.getTags(),payLoad.getTags());
        assertEquals(postActualData.getStatus(),payLoad.getStatus());


        //__________________________________________
        // 2- Send Get Request
        Response getResponse = given(spec).when().get("{first}/"+postActualData.getId());
        getResponse.prettyPrint();

        // Get Response Assertion
        PetStorePojo getActualData = getResponse.as(PetStorePojo.class);
        assertEquals(getResponse.statusCode(),200);
        assertEquals(getActualData.getId(),postActualData.getId());


        //__________________________________________
        // 3- Send Put Request
        payLoad.setStatus("sold");
        Response putResponse = given(spec).body(payLoad).when().put("{first}");
        putResponse.prettyPrint();

        // Put Response Assertion
        PetStorePojo putActualData= putResponse.as(PetStorePojo.class);
        assertEquals(putResponse.statusCode(),200);
        assertEquals(putActualData.getStatus(),"sold");

        //__________________________________________
        // 4- Send Delete Request
        Response deleteResponse = given(spec).when().delete("{first}/"+postActualData.getId());
        deleteResponse.prettyPrint();

        // Delete Response Assertion
        deleteResponse
                .then()
                .statusCode(200)
                .body("code",equalTo(200),
                        "type",equalTo("unknown"),
                        "message",equalTo("456"));

    }

}
