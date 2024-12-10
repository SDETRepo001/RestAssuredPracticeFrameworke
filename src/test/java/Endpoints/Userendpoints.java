package Endpoints;
import static io.restassured.RestAssured.given;

import Payloads.PostPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class Userendpoints {
    PostPayload postpayload;
    static public String token = "Bearer e0ad33a27af2be5ef6fb60647c1f76fba10258b0373027c39f9834d16f10c821";
    static public Response postRequest(PostPayload data)
    {
       Response response = given().header("Authorization", token )
                .body(data)
                .contentType(ContentType.JSON)
                .when().post(Routes.postURL);
       return response;
    }
    static public Response GetRequest (String id)
    {
        Response response = given().header("Authorization", token )
                .pathParam("id", id)
                .when().get(Routes.getURL );
        return response;
    }
    static public Response PutRequest ()
    {
        Response response = given().header("Authorization", token )
                .when().put(Routes.putURL);
        return response;
    }
    static public Response deleteRequest ()
    {
        Response response = given().header("Authorization", token )
                .when().delete(Routes.deleteURL);
        return response;
    }


}