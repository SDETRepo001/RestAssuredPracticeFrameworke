package Mathias;

import Endpoints.Userendpoints;
import Payloads.PostPayload;
import Utilities.DataProviders;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mozilla.javascript.ScriptRuntime.setName;

public class MyRequests {
    JSONObject body;
    String id;
    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostRequest(String name, String gender, String email, String status)
    {
        PostPayload postPayload = new PostPayload();

        postPayload.setName(name);
        postPayload.setGender(gender);
        postPayload.setEmail(email);
        postPayload.setStatus(status);

        Response response = Userendpoints.postRequest(postPayload);
        id =response.body().jsonPath().getString("id");
        Assert.assertEquals(response.getStatusCode(), 201);
        response.then().log().body();
    }
    @Test(priority =1, dependsOnMethods = "testPostRequest")
    public void testGetRequest(){
       Response response= Userendpoints.GetRequest(id);
       Assert.assertEquals(response.getStatusCode(),200);
       response.then().log().all();
    }
    @Test(priority = 3 ,dependsOnMethods = "testPostRequest")
    public void testDeleteRequest(){
       Response response= Userendpoints.deleteRequest(id);
       Assert.assertEquals(response.getStatusCode(),204);
    }

    @Test(priority = 2 ,dependsOnMethods = "testPostRequest")
    public void testPutRequest(){
        body=new JSONObject();
        PostPayload postPayload = new PostPayload();
        postPayload.setName("Amid");
        postPayload.setGender("male");
        postPayload.setEmail("amid@gmail.com");
        postPayload.setStatus("Active");

        body.put("name",postPayload.getName());
        body.put("gender",postPayload.getGender());
        body.put("email",postPayload.getName());
        body.put("gender",postPayload.getStatus());



        Response response= Userendpoints.putRequest(id,body);
        Assert.assertEquals(response.getStatusCode(),200);
        response.then().log().body();
    }

}
