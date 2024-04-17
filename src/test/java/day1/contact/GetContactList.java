package day1.contact;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetContactList {

    String token;
    @Test(priority = 1)
    public void login(){

        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath = "/users/login";

        Response response = given().contentType(ContentType.JSON).log().all().body("{\n" +
                "    \"email\": \"Akar@fake.com\",\n" +
                "    \"password\": \"myPassword\"\n" +
                "}").post();

        token = response.then().extract().path("token");
        response.prettyPrint();
        System.out.println(response.getStatusCode());

    }

    //https://thinking-tester-contact-list.herokuapp.com/contacts
    @Test(priority = 2)
    public void getContactList(){
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath = "/contacts";

        Response repo = given().header("Authorization","Bearer "+ token).log().all().get();

        repo.prettyPrint();
        System.out.println(repo.getStatusCode());
    }
}
