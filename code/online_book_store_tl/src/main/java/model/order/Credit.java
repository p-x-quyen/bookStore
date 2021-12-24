package model.order;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Credit extends Payment {
    String number;
    String creditType;
    Date expDate;

    public Credit() {
    }

    public Credit(String number, String creditType, Date expDate, float totalPrice, String type) {
        super(totalPrice, type);
        this.number = number;
        this.creditType = creditType;
        this.expDate = expDate;
    }

    public Credit(String number, String creditType, Date expDate, int id, float totalPrice, String type) {
        super(id, totalPrice, type);
        this.number = number;
        this.creditType = creditType;
        this.expDate = expDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
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
        return "Credit{" + "number=" + number + ", creditType=" + creditType + ", expDate=" + expDate + '}';
    }
    
    
}
