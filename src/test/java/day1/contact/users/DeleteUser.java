package day1.contact.users;

/*
* https://thinking-tester-contact-list.herokuapp.com/users/login
* "email": "Akar@fake.com",
    "password": "myPassword"
* */
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser {
    String token;
    @Test(priority = 1)
    public void login(){
        RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath="/users/login";

        Response resp = given().contentType(ContentType.JSON).log().all().body("{\n" +
                "    \"email\": \"Akar@fake.com\",\n" +
                "    \"password\": \"myPassword\"\n" +
                "}").post();
        token = resp.then().extract().path("token");
        System.out.println("Token "+token);
        resp.prettyPrint();
    }
    //https://thinking-tester-contact-list.herokuapp.com/users/me
    @Test(priority = 2)
    public void deleteUser(){
        RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath = "/users/me";

        Response response = given().header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON).log().all().delete();
        response.prettyPrint();
        System.out.println(response.getStatusCode());
    }
}
