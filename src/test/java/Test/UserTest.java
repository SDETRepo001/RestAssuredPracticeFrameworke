package Test;

import Endpoints.Routes;
import Endpoints.Userendpoints;
import Payloads.PostPayload;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.*;

public class UserTest {

    Routes routes = new Routes();
    static PostPayload postpayload ;
    static String id;


@BeforeClass
    public void data1 ()
    {
        Faker faker = new Faker();
        postpayload  = new PostPayload();
        postpayload.setName(faker.name().fullName());
        postpayload.setEmail(faker.internet().emailAddress());
        postpayload.setGender("male");
        postpayload.setStatus("Active");
    }


    @Test(priority = 1)
    public void testPost()
    {
        Response response = Userendpoints.postRequest(postpayload);
        Assert.assertEquals(response.getStatusCode(), 201);
        response.then().log().body();
        id = response.body().jsonPath().getString("id");
    }

    @Test(priority = 2)
    public void testRetriveUser()
    {
        Response response = Userendpoints.GetRequest(id);
        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().log().body();
    }



}
