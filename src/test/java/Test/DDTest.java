package Test;

import Endpoints.Userendpoints;
import Payloads.PostPayload;
import Utilities.DataProviders;
import Utilities.excelUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Utilities.excelUtil.readIdsFromExcel;
import static Utilities.excelUtil.writeIdToExcel;

public class DDTest  {

    static String id;
    static List<String> ids = new ArrayList<>();

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostrequest(String name, String gender, String email, String status) throws IOException {
        PostPayload postPayload = new PostPayload();

        postPayload.setName(name);
        postPayload.setGender(gender);
        postPayload.setEmail(email);
        postPayload.setStatus(status);

        Response response = Userendpoints.postRequest(postPayload);
        Assert.assertEquals(response.getStatusCode(), 201);
        response.then().log().body();

        String id = response.body().jsonPath().getString("id");
        ids.add(id);

        // Write the ID to the new Excel sheet
        writeIdToExcel("Sheet2", id);

    }
    @Test(priority = 2)
    void getRequest() throws IOException {

        List<String> idsFromExcel = readIdsFromExcel("Sheet2");

        for (String id : idsFromExcel) {
            Response getresponse = Userendpoints.GetRequest(id);
            Assert.assertEquals(getresponse.getStatusCode(), 200);
            getresponse.then().log().body();
        }
    }




   // @Test(dataProvider = "Data", dataProviderClass = DataProviders.class)
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
