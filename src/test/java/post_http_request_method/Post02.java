package post_http_request_method;
 /*
        When
            I send POST Request to the Url https://jsonplaceholder.typicode.com/todos
            with the request body {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false
                                   }
        Then
            Status code is 201
            And response body is like {
                                        "userId": 55,
                                        "title": "Tidy your room",
                                        "completed": false,
                                        "id": 201
                                      }
     */

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Todo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends JsonPlaceHolderBaseUrl {
   @Test
   public void post02(){
    //Set the base url
       spec.pathParams("first","todos");

       //Set the expected data
       Map<String, Object> expectedData = new HashMap<>();
       expectedData.put("userId",55);
       expectedData.put("title", "Tidy your room");
       expectedData.put("completed", false);

       //Sent a post request and get the response
      Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");

       //Tejn do the validation
       response.then().statusCode(201);

       Map<String,Object> actualData = response.as(HashMap.class);

       assertEquals(expectedData.get("userId"), actualData.get("userId"));
       assertEquals(expectedData.get("title"), actualData.get("title"));
       assertEquals(expectedData.get("completed"), actualData.get("completed"));

       System.out.println("actualData = " + actualData);
       System.out.println("expectedData = " + expectedData);
    }

    @Test
    public void test(){
        //Set the base url
        spec.pathParams("first","todos");


        //Set the expected data
      Todo expectedData = new Todo(55,"Tidy your room",false);

        //Send the Post rquest and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");

        //Do the validation
        response.then().statusCode(201);

        Todo actualData = response.as(Todo.class);

        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.isCompleted(), actualData.isCompleted());

    }


}
