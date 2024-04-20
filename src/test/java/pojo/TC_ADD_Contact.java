package pojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import users.Utils_data;

import static io.restassured.RestAssured.given;

public class TC_ADD_Contact {

    String token;

    @Test(priority = 1)
    public void login() {
        RestAssured.baseURI = API_Constants.baseURL;
        RestAssured.basePath = API_Constants.endPoint;

        Login_POJO lp = new Login_POJO();
        lp.setEmail("Akar@fake.com");
        lp.setPassword("myPassword");


        Response resp = given().contentType(ContentType.JSON).log().all().body(lp).post();
        token = resp.then().extract().path("token");
        System.out.println("Token " + token);
        resp.prettyPrint();
        System.out.println(resp.prettyPrint());

    }

    @Test(priority = 2)
    public void addContact() {
        RestAssured.baseURI = API_Constants.baseURL;
        RestAssured.basePath = API_Constants.addContact;

        Contacts_POJO cp = new Contacts_POJO();
        cp.setFirstName(Utils_data.firstName());
        cp.setLastName(Utils_data.lastName());
        cp.setBirthdate(Utils_data.birthdate());
        cp.setEmail(Utils_data.email());
        cp.setPhone(Utils_data.phoneNumber());
        cp.setStreet1(Utils_data.streetName());
        cp.setStreet2(Utils_data.streetName());
        cp.setCity(Utils_data.cityName());
        cp.setStateProvince(Utils_data.stateProvince());
        cp.setPostalCode(Utils_data.postalCode());
        cp.setCountry(Utils_data.countryName());

        Response resp = given().header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON).log().all().body(cp).post();
       String lstname = resp.then().extract().path("lastName");
       System.out.println(lstname);
        resp.prettyPrint();
        int statusCode = resp.getStatusCode();
        System.out.println(statusCode);

    }
}
