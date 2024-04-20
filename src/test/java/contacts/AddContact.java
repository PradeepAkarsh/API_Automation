package contacts;/*

* https://thinking-tester-contact-list.herokuapp.com/users/login
* "email": "Akar@fake.com",
    "password": "myPassword"
*/


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;
import users.Utils_data;

import static io.restassured.RestAssured.given;

public class AddContact {

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
    public void addContact1(){
        RestAssured.baseURI = API_baseURLs.baseURL;
        RestAssured.basePath = API_baseURLs.addContact;

        UserDetail_POJO udp = new UserDetail_POJO();
        udp.setFirstName(Utils_data.firstName());
        udp.setLastName(Utils_data.lastName());
        udp.setBirthdate(Utils_data.birthdate());
        udp.setEmail(Utils_data.email());
        udp.setPhone(Utils_data.phoneNumber());
        udp.setStreet1(Utils_data.streetName());
        udp.setStreet2(Utils_data.streetName());
        udp.setCity(Utils_data.cityName());
        udp.setStateProvince(Utils_data.stateProvince());
        udp.setPostalCode(Utils_data.postalCode());
        udp.setCountry(Utils_data.countryName());
        Response resp = given().header("Authorization","Bearer "+ token)
                .contentType(ContentType.JSON).log().all().body(udp).post();
        resp.prettyPrint();
        int statusCode = resp.getStatusCode();
        System.out.println("First Name Go extracted : "+udp.getFirstName());
        Assert.assertEquals(statusCode, 201,"Invalid StatusCode");
    }
}
