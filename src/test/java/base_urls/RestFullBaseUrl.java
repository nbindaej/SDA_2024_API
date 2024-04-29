package base_urls;

import com.sun.net.httpserver.Request;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class RestFullBaseUrl {
    protected RequestSpecification spec;
    @BeforeMethod
    public void setUp(){
       spec = new RequestSpecBuilder()
                                       .setBaseUri("https://restful-booker.herokuapp.com")
                                       .setContentType(ContentType.JSON)
                                       .build();
    }

}
