package get_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Todo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

/*
 Given
        https://jsonplaceholder.typicode.com/todos/2
 When I send a Get Request

 Then the actual data should be as following;
    {
    "userId": 1,
    "id": 2,
    "title": "quis ut nam facilis et officia qui",
    "completed": false   ====> it looks like a map
}

 */

public class Get08 extends JsonPlaceHolderBaseUrl {

    @Test
    public void get08(){
        //1.Set the url
        spec.pathParams("first","todos","second",2);

        //2.Set the expected data //where do we get it? from task// it is given by Business Analyst
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed", false);

        System.out.println(expectedData);


        //3.Send the request
        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint(); // it is Json response. But our expected data is Java.
                                // So IF we want to compare them, we need to conevert jva to Json.
                                // It is serialization

        //If we are converting Json to Java -- This is DE-Serialization
        //If we are converting Java to Json -- Serialization

        //4.Do the validation
        Map<String, Object> actualData = response.as(HashMap.class); // take response, convert to Java

        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("id"),actualData.get("id"));

    }
    @Test
    public void test(){
        //set the url
        spec.pathParams("first","todos","second",2);

        //Set the expected data
        Todo expectedTodo = new Todo(1,2,"quis ut nam facilis et officia qui",false);

        //Send the Get request and get the response
       Response response =  given().spec(spec).when().get("/{first}/{second}");

//     This is todo reference  = this part is returning Todo Object
       Todo actualTodo         = response.as(Todo.class); // we converted the JSon data to Todo

        assertEquals(expectedTodo.getId(),actualTodo.getId());
        assertEquals(expectedTodo.getUserId(),actualTodo.getUserId());
        assertEquals(expectedTodo.getTitle(),actualTodo.getTitle());
        assertEquals(expectedTodo.isCompleted(),actualTodo.isCompleted());

        System.out.println(expectedTodo);
        System.out.println(actualTodo);

        //Serialization => to convert Java to JSon
        //De-Serialization =>JSon to Java

    }








//    @Test
//    public void test(){
//
//        //1. Set the base url
//        spec.pathParams("first","todos","second",2);
//
//        //2.Set the expected data
//            Todo expectedTodo = new Todo(1,2,"quis ut nam facilis et officia qui",false);
//
//            //Send teh request get the response
//        Response response = given().spec(spec).when().get("/{first}/{second}");
//
//        Todo actualTodo = response.as(Todo.class); //we didnt create a new obj. directly assign to smt
//                                                    // we convert the data here
//
//
//        assertEquals(expectedTodo.getId(), actualTodo.getId());
//        assertEquals(expectedTodo.getTitle(),actualTodo.getTitle());
//        assertEquals(expectedTodo.getUserId(),actualTodo.getUserId());
//        assertEquals(expectedTodo.isCompleted(),actualTodo.isCompleted());
//
//        System.out.println("expectedTodo" + expectedTodo);
//        System.out.println("actualTodo"+ actualTodo);
//
//
//        }
//
//


    }




