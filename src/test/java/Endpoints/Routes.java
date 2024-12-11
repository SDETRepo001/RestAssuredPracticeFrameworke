package Endpoints;

public class Routes {
    static public String baseURl = "https://gorest.co.in/public";
   static public String postURL = baseURl + "/v2/users";
    static public String getURL = baseURl + "/v2/users/{id}";
    static public String putURL = baseURl + "/v2/users/{id}";
    static public String deleteURL = baseURl + "/v2/users/{id}";
    ////****************************Omid*****
    static public String  baseURLFake="https://fakerestapi.azurewebsites.net/api/v1/";
    static public String  getBooks=baseURLFake+"Books";
    static public String postBooks=baseURLFake+"Books";
    static public String  getBook=baseURLFake+"Books/{id}";
    static public String putBook=baseURLFake+"Books/{id}";
    static public String deleteBook=baseURLFake+"Books/{id}";



}
