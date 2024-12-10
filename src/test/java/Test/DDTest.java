package Test;

import Endpoints.Userendpoints;
import Payloads.PostPayload;
import Utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDTest {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostrequest(String name, String gender, String email, String status)
    {
        PostPayload postPayload = new PostPayload();

        postPayload.setName(name);
        postPayload.setGender(gender);
        postPayload.setEmail(email);
        postPayload.setStatus(status);

        Response response = Userendpoints.postRequest(postPayload);
        Assert.assertEquals(response.getStatusCode(), 201);
        response.then().log().body();
    }

    @Test(dataProvider = "Data", dataProviderClass = DataProviders.class)
    void print(String name, String gender, String email, String status) {
        PostPayload postPayload = new PostPayload();
        postPayload.setName(name);
        postPayload.setGender(gender);
        postPayload.setEmail(email);
        postPayload.setStatus(status);
        System.out.println("Name: " + postPayload.getName() + "\n" +
                "Email: " + postPayload.getEmail() + "\n" +
                "Gender: " + postPayload.getGender() + "\n" +
                "Status: " + postPayload.getStatus());
    }
}
