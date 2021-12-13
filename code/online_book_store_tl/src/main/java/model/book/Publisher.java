package model.book;

/**
 *
 * @author Administrator
 */
public class Publisher {
    int id;
    String name;
    String address;
    String email;

    public Publisher() {
    }

    public Publisher(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Publisher(int id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Publisher{" + "id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + '}';
    }
    
    
}
