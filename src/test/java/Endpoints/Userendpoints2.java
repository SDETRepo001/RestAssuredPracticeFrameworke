package Endpoints;

import Payloads.PostPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class Userendpoints2 {

    static ResourceBundle getURL()
    {
        ResourceBundle routes =ResourceBundle.getBundle("url");
        return  routes;
    }

    PostPayload postpayload;
    static public String token = "Bearer e0ad33a27af2be5ef6fb60647c1f76fba10258b0373027c39f9834d16f10c821";
    static public Response postRequest(PostPayload data)
    {
        String postURL = getURL().getString("postURL");

       Response response = given().header("Authorization", token )
                .body(data)
                .contentType(ContentType.JSON)
                .when().post(postURL);
       return response;
    }
    static public Response GetRequest (String id)
    {
        String getURL = getURL().getString("getURL");

        Response response = given().header("Authorization", token )
                .pathParam("id", id)
                .when().get(getURL );
        return response;
    }
    static public Response PutRequest (PostPayload data, String id)
    {
        String putURL = getURL().getString("putURL");

        Response response = given().header("Authorization", token )
                .pathParam("id", id)
                .body(data)
                .contentType(ContentType.JSON)
                .when().put(putURL);
        return response;
    }
    static public Response deleteRequest (String id)
    {
        String deleteURL = getURL().getString("deleteURL");

        Response response = given().header("Authorization", token )
                .pathParam("id", id).when().delete(deleteURL);
        return response;
    }
/// **************************************** Omid endpoints
// ************************ Rahim Endpoints

}
