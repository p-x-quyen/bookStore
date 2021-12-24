package model.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import model.Pair;
import model.book.BookItem;

/**
 *
 * @author Administrator
 */
public class Cart {
    int id;
    int totalQuantity;
    float totalPrice;
    Date createDate;
    List<Pair<BookItem, Integer>> listBookItems;

    public Cart() {
        this.totalQuantity = 0;
        this.totalPrice = 0;
        this.listBookItems = new ArrayList<>();
    }

    public Cart(int totalQuantity, float totalPrice, Date createDate, List<Pair<BookItem, Integer>> listBookItems) {
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
        this.listBookItems = listBookItems;
    }

    public Cart(int id, int totalQuantity, float totalPrice, Date createDate, List<Pair<BookItem, Integer>> listBookItems) {
        this.id = id;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
        this.listBookItems = listBookItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Pair<BookItem, Integer>> getListBookItems() {
        return listBookItems;
    }

    public void setListBookItems(List<Pair<BookItem, Integer>> listBookItems) {
        this.listBookItems = listBookItems;
    }
    
    public Pair<BookItem, Integer> getBookItemPair(int id) {
        for (Pair<BookItem, Integer> bookItemPair: this.listBookItems) {
            if (bookItemPair.getKey().getId() == id) {
                return bookItemPair;
            }
        }
        
        return null;
    }
    
    public void addBookItemPair(Pair<BookItem, Integer> addedPair) {
        for (Pair<BookItem, Integer> bookItemPair: this.listBookItems) {
            if (bookItemPair.getKey().getId() == addedPair.getKey().getId()) {
                bookItemPair.setValue(bookItemPair.getValue() + addedPair.getValue());
                
                this.totalQuantity += addedPair.getValue();
                float price = addedPair.getKey().getPrice();
                float discount = Float.parseFloat(addedPair.getKey().getDiscount()) / 100;
                this.totalPrice += (price - (discount * price));
                
                return;
            }
        }
        
        this.listBookItems.add(addedPair);
    }

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", totalQuantity=" + totalQuantity + ", totalPrice=" + totalPrice + ", createDate=" + createDate + ", listBookItems=" + listBookItems + '}';
    }
    
}
