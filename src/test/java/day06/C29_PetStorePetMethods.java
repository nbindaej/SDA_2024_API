package day06;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.petstore.Category;
import pojos.petstore.PetStoreResponse;

import static io.restassured.RestAssured.given;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class C29_PetStorePetMethods extends PetStoreBaseUrl {
    int id= 2133;
    PetStoreResponse payLoad;
    @Test(priority = 1)
    public void createPetTest(){
        // Set Url
        spec.pathParam("first","pet");

        // Set expected Data

        String payLoadStr = """
                {
                  "id": 2133,
                  "category": {
                    "id": 3,
                    "name": "Doggie"
                  },
                  "name": "Aldo",
                  "photoUrls": [
                    "string","another"
                  ],
                  "tags": [
                    {
                      "id": 1,
                      "name": "Cute"
                    },
                    {
                      "id": 2,
                      "name": "Cheap"
                    }
                  ],
                  "status": "available"
                }
                """;

        payLoad = convertJsonToJava(payLoadStr,PetStoreResponse.class);

        // Send Request and get response
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();
        response.then().statusCode(200);

    }
    @Test(dependsOnMethods = "createPetTest",priority = 2)
    public void getPetTest(){
        // Set Url
        spec.pathParams("first","pet","second",id);
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(dependsOnMethods = "createPetTest",priority = 3)
    public void updatePetTest(){

        spec.pathParams("first","pet");

        payLoad.setName("snow");
        payLoad.setStatus("sold");
        payLoad.getTags().get(0).setName("sick");

        Response response = given(spec).body(payLoad).when().put("{first}");
        response.prettyPrint();
    }
    @Test(dependsOnMethods = "createPetTest",priority = 4)
    public void deletePetTest(){
        spec.pathParams("first","pet","second",id);
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        response.then().statusCode(200);
    }
}
