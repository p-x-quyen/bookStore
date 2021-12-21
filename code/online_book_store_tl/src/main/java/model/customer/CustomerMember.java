package model.customer;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class CustomerMember extends Customer {
    String cartNumber;

    public CustomerMember() {
    }

    public CustomerMember(String cartNumber, String email, String phone, Date dateOfBirth, String type, String gender, Account account, Address address, FullName fullName) {
        super(email, phone, dateOfBirth, type, gender, account, address, fullName);
        this.cartNumber = cartNumber;
    }

    public CustomerMember(String cartNumber, int id, String email, String phone, Date dateOfBirth, String type, String gender, Account account, Address address, FullName fullName) {
        super(id, email, phone, dateOfBirth, type, gender, account, address, fullName);
        this.cartNumber = cartNumber;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "CustomerMember{" + "cartNumber=" + cartNumber + '}';
    }

    
    
}
