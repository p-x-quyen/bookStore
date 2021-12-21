package model.customer;

/**
 *
 * @author Administrator
 */
public class Address {
    int id;
    String city;
    String district;
    String street;
    String houseNumber;

    public Address() {
    }

    public Address(String city, String district, String street, String houseNumber) {
        this.city = city;
        this.district = district;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Address(int id, String city, String district, String street, String houseNumber) {
        this.id = id;
        this.city = city;
        this.district = district;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", city=" + city + ", district=" + district + ", street=" + street + ", houseNumber=" + houseNumber + '}';
    }
    
    
}
