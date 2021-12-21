package model.customer;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Customer {
    int id;
    String email;
    String phone;
    Date dateOfBirth;
    String type;
    String gender;
    
    Account account;
    Address address;
    FullName fullName;

    public Customer() {
    }

    public Customer(String email, String phone, Date dateOfBirth, String type, String gender, Account account, Address address, FullName fullName) {
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
        this.gender = gender;
        this.account = account;
        this.address = address;
        this.fullName = fullName;
    }

    public Customer(int id, String email, String phone, Date dateOfBirth, String type, String gender, Account account, Address address, FullName fullName) {
        this.email = email;
        this.id = id;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
        this.gender = gender;
        this.account = account;
        this.address = address;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", email=" + email + ", phone=" + phone + ", dateOfBirth=" + dateOfBirth + ", type=" + type + ", gender=" + gender + ", account=" + account + ", address=" + address + ", fullName=" + fullName + '}';
    }

    
}
