package Endpoints;

import Payloads.BookPayloadOmid;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FakerBookEndpointOmid {


    public static Response getBooks(){

       return given()
                .when().get(Routes.getBooks);
    }

    public static Response postBooks(BookPayloadOmid data){
        return given()
                .body(data).
                contentType(ContentType.JSON)
                .when().post(Routes.postBooks);
    }

    public static Response getBook(int id ){

        return given()
                .pathParam("id",id)
                .when().get(Routes.getBook);
    }

    public static Response putbook(BookPayloadOmid data,int id ){
        return given()
                .body(data)
                .pathParam("id",id)
                .contentType(ContentType.JSON)
                .when().put(Routes.putBook);
    }

    public static Response deleteBook (int id){
        return given()
                .pathParam("id",id)
                .when().delete(Routes.deleteBook);
    }


}
