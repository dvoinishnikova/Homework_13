package homework13.jsonplaceholder;

public class Address {

    private final String street;
    private final String suite;
    private final String city;
    private final String zipcode;


    public Address(String street, String suite, String city,
                   String zipcode) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipcode=" + zipcode +
                '}';
    }
}