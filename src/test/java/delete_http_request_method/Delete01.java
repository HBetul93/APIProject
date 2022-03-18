package delete_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
 /*
        When
            I send DELETE Request to the Url https://jsonplaceholder.typicode.com/todos/198
        Then
            Status code is 200
            And Response body is {}
    */


public class Delete01 extends JsonPlaceHolderBaseUrl {

    @Test
    public void delete01(){
        //Set the base url
        spec.pathParams("first","todos","second",198);

        //Send the Delete request get the response
        Response response= given().spec(spec).when().delete("/{first}/{second}");

        response.then().statusCode(200);

        //do the validation
        response.prettyPrint();

    }
}
