package users;

import com.github.javafaker.Faker;

import java.util.Locale;

public class Utils_data {


    public static String userName(){
        return "Akar@fake.com";
    }

    public static String password(){
        return "myPassword";
    }
    public static String firstName(){
        Faker faker = new Faker( new Locale("en-IND"));
        return faker.name().firstName();
    }
    public static String lastName(){
        Faker faker = new Faker( new Locale("en-IND"));
        return faker.name().lastName();
    }

    public static String birthdate() {
        return ("1970-01-01");
    }

    public static String email(){
        Faker faker = new Faker( new Locale("en-IND"));
        return faker.name().firstName()+"@testmail.com";
    }

    public static String phoneNumber(){
        Faker faker = new Faker();
        return faker.number().digits(10);
    }

    public static String streetName(){
        Faker faker = new Faker( new Locale("en-IND"));
        return faker.address().streetName();
    }
    public static String cityName(){
        Faker faker = new Faker( new Locale("en-IND"));
        return faker.address().cityName();
    }
    public static String postalCode(){
        Faker faker = new Faker();
        return faker.address().zipCode();
    }
    public static String countryName(){
        Faker faker = new Faker( new Locale("en-IND"));
        return faker.address().country();
    }
    //stateProvince
    public static String stateProvince(){
        Faker faker = new Faker( new Locale("en-IND"));
        return faker.address().state();
    }

}
