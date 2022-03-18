package post_http_request_method;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Todo;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
public class Post03 extends JsonPlaceHolderBaseUrl {
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
                                     username: admin ===> we need authentication
                                     password: 1234
    */

    //authentication : access to the application
    //authorization : level of access

    @Test
    public void post(){
        //Set the base url
        spec.pathParams("first","todos");

        //Set the expected data
        Map<String, Object> expextedData = new HashMap<>();

         expextedData.put("userId",55);
         expextedData.put("title","Tidy your room");
         expextedData.put("completed",false);

         //Send the post request and get the response
        //Wehn we use auth, we can use different type of  auths in API.
        // we use "basic auth" within requires username and password

        Response response =given().spec(spec).auth().basic("admin","1234").
                contentType(ContentType.JSON).
                body(expextedData).
                when().post("/{first}");

        //validation
        response.then().statusCode(201);

        //1.validation
        //in API testing, we need to validate 2 things: status code and body

        Map<String, Object> actualData = response.as(HashMap.class);

        System.out.println("actualData = " + actualData);

        System.out.println("expextedData = " + expextedData);

        assertEquals(expextedData.get("userId"), actualData.get("userId"));
        assertEquals(expextedData.get("title"), actualData.get("title"));
        assertEquals(expextedData.get("completed"), actualData.get("completed"));

        //2.way using JSoPath

        JsonPath json = response.jsonPath();
        assertEquals(expextedData.get("userId"),json.getInt("userId"));
        assertEquals(expextedData.get("title"),json.getInt("title"));
        assertEquals(expextedData.get("completed"),json.getInt("completed"));

        //3.way of validation (with hemthcrech matcher)
        response.then().body("userId", equalTo(expextedData.get("userId"))).
                body("title", equalTo(expextedData.get("title"))).
                body("completed", equalTo(expextedData.get("completed")));

        //4.validation
        Todo todo = response.as(Todo.class);

        assertEquals(expextedData.get("userId"),actualData.get("userId"));
        assertEquals(expextedData.get("title"),actualData.get("title"));
        assertEquals(expextedData.get("completed"),actualData.get("completed"));



    }

//        //Send the Post request and get the response
//        //When we use auth, we can use diffrent type of auths in API. we use basic auth which requires username and password

}
