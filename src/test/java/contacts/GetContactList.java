package contacts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;
import users.Utils_data;

import static io.restassured.RestAssured.given;

public class GetContactList {

    String token;
    @Test(priority = 1)
    public void login(){

        RestAssured.baseURI = API_baseURLs.baseURL;
        RestAssured.basePath = API_baseURLs.login;

        Userlogin_POJO ulp = new Userlogin_POJO();
        ulp.setEmail(Utils_data.userName());
        ulp.setPassword(Utils_data.password());
        Response response = given().contentType(ContentType.JSON).log().all().body(ulp).post();
        token = response.then().extract().path("token");
        response.prettyPrint();
        System.out.println(response.getStatusCode());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200,"Invalid StatusCode");

    }

    //https://thinking-tester-contact-list.herokuapp.com/contacts
    @Test(priority = 2)
    public void getContactList(){
        RestAssured.baseURI = API_baseURLs.baseURL;
        RestAssured.basePath = API_baseURLs.getContactsList;
        Response repo = given().header("Authorization","Bearer "+ token).log().all().get();
        repo.prettyPrint();
        System.out.println(repo.getStatusCode());
        int statusCode = repo.getStatusCode();
        Assert.assertEquals(statusCode, 200,"Invalid StatusCode");
    }
}
