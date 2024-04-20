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

public class UserLogin {
    String token;
    @Test(priority = 1)
    public void login(){
        RestAssured.baseURI= API_baseURL.baseURL;
        RestAssured.basePath= API_baseURL.login;
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
}
