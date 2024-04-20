package users;/*
* https://thinking-tester-contact-list.herokuapp.com/users/login
* "email": "Akar@fake.com",
    "password": "myPassword"
* */
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LogOutUser {
    String token;
    @Test(priority = 1)
    public void login(){
        RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath="/users/login";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", Utils_data.userName());
        jsonObject.put("password", Utils_data.password());
        Response response = given().contentType(ContentType.JSON).log().all().body(jsonObject).post();
        token = response.then().extract().path("token");
        System.out.println("Token "+token);
        response.prettyPrint();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200,"Invalid StatusCode");
    }
    //https://thinking-tester-contact-list.herokuapp.com/users/logout
    @Test(priority = 2)
    public void logOutUser(){
        RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath = "/users/logout";
        Response response = given().header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON).log().all().post();
        response.prettyPrint();
        System.out.println(response.getStatusCode());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200,"Invalid StatusCode");
    }
}
