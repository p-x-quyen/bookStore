package model.book;

/**
 *
 * @author Administrator
 */
public class Author {
    int id;
    String fullName;
    String biography;
    String address;

    public Author() {
    }

    public Author(String fullName, String biography, String address) {
        this.fullName = fullName;
        this.biography = biography;
        this.address = address;
    }

    public Author(int id, String fullName, String biography, String address) {
        this.id = id;
        this.fullName = fullName;
        this.biography = biography;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", fullName=" + fullName + ", biography=" + biography + ", address=" + address + '}';
    }
    
}
