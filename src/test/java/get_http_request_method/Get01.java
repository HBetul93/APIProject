package get_http_request_method;
import base_urls.HerokuAppBaseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import static io.restassured.RestAssured.given;
public class Get01 extends HerokuAppBaseUrl {
    /*
           Given https://restful-booker.herokuapp.com/booking/3 => end point
           When user sends a GET request to the url
           Then HTTP status code should be 200
           And   content type should be Json
           And status line should be HTTP/1.1 200 OK
    */
    @Test
    public void get01() { //we are not going to use this way a lot
                         //This is primitive way that we do not prefer!
        //1. Set the url
        String endpoint = "https://restful-booker.herokuapp.com/booking/3";

        //2.Set the expected data
        //3. send the request and Get the response
        Response response = given().when().get(endpoint);
        response.prettyPrint();
        //4. do the validation and assertion
    }

    @Test
    public void test() {
        //1/ Set the base url
        spec.pathParams("firstPar", "booking", "secPar", 3 );
        //2. set the expected data
        //3. Send the Get request and get the response
        Response response = given().spec(spec).when().get("/{firstPar}/{secPar}");
        response.prettyPrint();
        //4. do the validation
        response.then().statusCode(200).statusLine("HTTP/1.1 200 OK").contentType(ContentType.JSON);
        System.out.println(response.headers());
    }
}