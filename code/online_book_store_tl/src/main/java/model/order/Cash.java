package model.order;

/**
 *
 * @author Administrator
 */
public class Cash extends Payment {
    String cashTendered;

    public Cash() {
    }

    public Cash(String cashTendered, float totalPrice, String type) {
        super(totalPrice, type);
        this.cashTendered = cashTendered;
    }

    public Cash(String cashTendered, int id, float totalPrice, String type) {
        super(id, totalPrice, type);
        this.cashTendered = cashTendered;
    }

    public String getCashTendered() {
        return cashTendered;
    }

    public void setCashTendered(String cashTendered) {
        this.cashTendered = cashTendered;
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
        return "Cash{" + "cashTendered=" + cashTendered + '}';
    }
    
    
}
