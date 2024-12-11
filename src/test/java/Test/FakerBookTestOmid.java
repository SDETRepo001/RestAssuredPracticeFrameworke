package Test;

import Endpoints.FakerBookEndpointOmid;
import Payloads.BookPayloadOmid;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FakerBookTestOmid {

    static  BookPayloadOmid book=new BookPayloadOmid();
    Faker faker=new Faker();
    @Test
     void testGetBooks(){
        Response res= FakerBookEndpointOmid.getBooks();
        Assert.assertEquals(res.getStatusCode(),200);
        res.then().log().all();
    }

    @Test(priority=1)
     void testPostBook(){

        book.setId(faker.number().numberBetween(1,110));
        book.setTitle(faker.name().title());
        book.setDescription(faker.lorem().sentence());
        book.setPageCount(faker.number().numberBetween(5,10000));
        book.setExcerpt(faker.lorem().sentence());
        book.setPublishDate("2024-11-18T05:17:39.2440113+00:00");

        Response res = FakerBookEndpointOmid.postBooks(book);
        res.then().statusCode(200);
        Assert.assertEquals(res.body().jsonPath().getString("title"), book.getTitle());

    }
    @Test(priority = 2)
    void testGetBook (){

        Response res = FakerBookEndpointOmid.getBook(book.getId());
        res.then().statusCode(200);
        res.then().log().all();
     //   Assert.assertEquals(res.body().jsonPath().getInt("pageCount"),book.getPageCount());
    }

    @Test(priority = 3)
    void testPutBook(){
        String newTitle=faker.name().title();
        book.setTitle(newTitle);
        Response res=FakerBookEndpointOmid.putbook(book,book.getId());
        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.body().jsonPath().getString("title"),newTitle);
    }

    @Test(priority = 4)
    void testDeleteBook(){
        Response res=FakerBookEndpointOmid.deleteBook(book.getId());
        res.then().statusCode(200);
    }


}
