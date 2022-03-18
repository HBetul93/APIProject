package put_http_request_method;

 /*
         When
            I send PUT Request to the Url https://jsonplaceholder.typicode.com/todos/198
            with the PUT Request body like {
                                            "userId": 21,
                                            "title": "Walk the dog",
                                            "completed": true
                                           }
         Then
           Status code is 200
           And response body is like   {
                                        "userId": 21,
                                        "title": "Walk the dog",
                                        "completed": true,
                                        "
                                       }
     */
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojos.Todo;
import test_data.JsonPlaceHolderData;
import base_urls.JsonPlaceHolderBaseUrl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderData.expectedData;

public class Put01 extends JsonPlaceHolderBaseUrl {

    @Test
    public void put01(){
        //Set the base url
        spec.pathParams("first", "todos","second",198);

        //Set the expected data
        Map<String,Object> expectedData = expectedData();
        //2.way to set the data
        Todo expectedTodo = new Todo(21,"Walk the dog",true);

        //Sent the Put request and get the response

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");

        //do the validation
        response.then().statusCode(200);
        response.prettyPrint();

        //1.validate with the body (hemcrest mathcer)
        response.then().body("userId",equalTo(expectedTodo.getUserId())).
                body("title",equalTo("Walk the dog")).
                body("completed", equalTo(true ));

        //2.validate with the Map
        Map<String,Object> actualDataMap = response.as(HashMap.class);

        assertEquals(expectedTodo.getUserId(),actualDataMap.get("userId"));
        assertEquals(expectedTodo.getTitle(),actualDataMap.get("title"));
        assertEquals(expectedTodo.isCompleted(),actualDataMap.get("completed"));

        //3.way with Pojo
        Todo actualTodo = response.as(Todo.class);

        assertEquals(expectedTodo.getTitle(), actualTodo.getTitle());
        assertEquals(expectedTodo.getUserId(),actualTodo.getUserId());
        assertEquals(expectedTodo.isCompleted(),actualTodo.isCompleted());

        //4.Way with Json path

        JsonPath json = response.jsonPath();

        assertEquals(expectedTodo.getUserId(),json.getInt("userId"));
        assertEquals(expectedTodo.getTitle(),json.getString("title"));
        assertEquals(expectedTodo.isCompleted(),json.getBoolean("completed"));






    }


}
