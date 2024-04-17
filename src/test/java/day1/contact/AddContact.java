package day1.contact;
/*

* https://thinking-tester-contact-list.herokuapp.com/users/login
* "email": "Akar@fake.com",
    "password": "myPassword"
*/


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddContact {

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
    public void addContact(){
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath = "/contacts";

        Response resp = given().header("Authorization","Bearer "+ token).contentType(ContentType.JSON).log().all().body("{\n" +
                "    \"firstName\": \"Khalil\",\n" +
                "    \"lastName\": \"Ah\",\n" +
                "    \"birthdate\": \"1970-01-01\",\n" +
                "    \"email\": \"jdoe@fake.com\",\n" +
                "    \"phone\": \"8005555555\",\n" +
                "    \"street1\": \"1 Main St.\",\n" +
                "    \"street2\": \"Apartment A\",\n" +
                "    \"city\": \"Anytown\",\n" +
                "    \"stateProvince\": \"KS\",\n" +
                "    \"postalCode\": \"12345\",\n" +
                "    \"country\": \"USA\"\n" +
                "}").post();


        resp.prettyPrint();
        System.out.println(resp.getStatusCode());
    }
}
