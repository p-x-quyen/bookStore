package model.order;

/**
 *
 * @author Administrator
 */
public class Payment {
    int id;
    float totalPrice;
    String type;

    public Payment() {
    }

    public Payment(float totalPrice, String type) {
        this.totalPrice = totalPrice;
        this.type = type;
    }

    public Payment(int id, float totalPrice, String type) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", totalPrice=" + totalPrice + ", type=" + type + '}';
    }
    
    
}
