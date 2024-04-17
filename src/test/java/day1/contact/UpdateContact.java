package day1.contact;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateContact {

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

    //https://thinking-tester-contact-list.herokuapp.com/contacts/
    @Test(priority = 2)
    public void updateContact(){
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath = "/contacts/";

        Response repo = given().header("Authorization","Bearer "+ token).contentType(ContentType.JSON).log().all().body("{\n" +
                "    \"firstName\": \"Rajesh\",\n" +
                "    \"lastName\": \"Miller\",\n" +
                "    \"birthdate\": \"1992-02-02\",\n" +
                "    \"email\": \"amiller@fake.com\",\n" +
                "    \"phone\": \"8005554242\",\n" +
                "    \"street1\": \"13 School St.\",\n" +
                "    \"street2\": \"Apt. 5\",\n" +
                "    \"city\": \"Washington\",\n" +
                "    \"stateProvince\": \"QC\",\n" +
                "    \"postalCode\": \"A1A1A1\",\n" +
                "    \"country\": \"Canada\"\n" +
                "}").put();

        repo.prettyPrint();
        System.out.println(repo.getStatusCode());
    }
}
