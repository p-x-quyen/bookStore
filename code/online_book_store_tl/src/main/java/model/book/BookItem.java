package model.book;

/**
 *
 * @author Administrator
 */
public class BookItem {
    int id;
    String image;
    float price;
    String discount;
    
    Book book;

    public BookItem() {
    }

    public BookItem(String image, float price, String discount, Book book) {
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.book = book;
    }

    public BookItem(int id, String image, float price, String discount, Book book) {
        this.id = id;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookItem{" + "id=" + id + ", image=" + image + ", price=" + price + ", discount=" + discount + ", book=" + book + '}';
    }
    
    
}
