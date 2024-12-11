package Endpoints;
import static io.restassured.RestAssured.given;

import Payloads.PostPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

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
    static public Response PutRequest (String id)
    {
        Response response = given().header("Authorization", token )
                .pathParam("id", id)
                .when().put(Routes.putURL);
        return response;
    }
    static public Response deleteRequest (String id)
    {
        Response response = given().header("Authorization", token )
                .pathParam("id", id)
                .when().delete(Routes.deleteURL);
        return response;
    }

    static public Response putRequest (String id, JSONObject boby)
    {
        Response response = given().header("Authorization", token )
                .body(boby)
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when().put(Routes.putURL);
        return response;
    }

}
