package contacts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import users.Utils_data;

import static io.restassured.RestAssured.given;

public class UpdateContact {

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

    //https://thinking-tester-contact-list.herokuapp.com/contacts/
    @Test(priority = 2)
    public void updateContact(){
        RestAssured.baseURI = API_baseURLs.baseURL;
        RestAssured.basePath = API_baseURLs.updateContact;

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
        Response repo = given().header("Authorization","Bearer "+ token)
                .contentType(ContentType.JSON).log().all().body(udp).put();
        repo.prettyPrint();
        System.out.println(repo.getStatusCode());
        int statusCode = repo.getStatusCode();
        Assert.assertEquals(statusCode, 503,"Server Problem Not Resolved");
    }
}
