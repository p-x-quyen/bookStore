package model.customer;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class CustomerNew extends Customer{
    String note;

    public CustomerNew() {
    }

    public CustomerNew(String note, String email, String phone, Date dateOfBirth, String type, String gender, Account account, Address address, FullName fullName) {
        super(email, phone, dateOfBirth, type, gender, account, address, fullName);
        this.note = note;
    }

    public CustomerNew(String note, int id, String email, String phone, Date dateOfBirth, String type, String gender, Account account, Address address, FullName fullName) {
        super(id, email, phone, dateOfBirth, type, gender, account, address, fullName);
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        return "CustomerNew{" + "note=" + note + '}';
    }

    
    
}
