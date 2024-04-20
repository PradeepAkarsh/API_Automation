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

public class UpdateUser {
    String token;
    @Test(priority = 1)
    public void login(){
        RestAssured.baseURI = API_baseURL.baseURL;
        RestAssured.basePath= API_baseURL.login;

        LoginUser_POJO lp = new LoginUser_POJO();
        lp.setEmail(Utils_data.userName());
        lp.setPassword(Utils_data.password());
        Response response = given().contentType(ContentType.JSON).log().all().body(lp).post();
        token = response.then().extract().path("token");
        System.out.println("Token "+token);
        response.prettyPrint();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200,"Invalid StatusCode");
    }

    //https://thinking-tester-contact-list.herokuapp.com/users
    @Test(priority = 2)
    public void updateUser(){
        RestAssured.baseURI = API_baseURL.baseURL;
        RestAssured.basePath = API_baseURL.updateUser;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", Utils_data.firstName());
        jsonObject.put("lastName", Utils_data.lastName());
        jsonObject.put("email", Utils_data.email());
        jsonObject.put("password", Utils_data.password());
        Response response = given().header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON).log().all().body(jsonObject).patch();
        response.prettyPrint();
        System.out.println(response.getStatusCode());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200,"Invalid StatusCode");
    }
}
