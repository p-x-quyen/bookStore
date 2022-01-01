package model.order;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Order {
    int id;
    Date createDate;
    String status;
    
    Cart cart;

    public Order() {
    }

    public Order(int id, Date createDate, String status) {
        this.id = id;
        this.createDate = createDate;
        this.status = status;
    }

    public Order(Date createDate, String status, Cart cart) {
        this.createDate = createDate;
        this.status = status;
        this.cart = cart;
    }

    public Order(int id, Date createDate, String status, Cart cart) {
        this.id = id;
        this.createDate = createDate;
        this.status = status;
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", createDate=" + createDate + ", status=" + status + ", cart=" + cart + '}';
    }
    
    
}
