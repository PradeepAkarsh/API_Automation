package pojo;

public class Contacts_POJO {

    /*
    * {
    "firstName": "John",
    "lastName": "Doe",
    "birthdate": "1970-01-01",
    "email": "jdoe@fake.com",
    "phone": "8005555555",
    "street1": "1 Main St.",
    "street2": "Apartment A",
    "city": "Anytown",
    "stateProvince": "KS",
    "postalCode": "12345",
    "country": "USA"
}*/
    private String firstName;
    private String lastName;
    private String birthdate;
    private String email;
    private String phone;
    private String street1;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String street2;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
}
