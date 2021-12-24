package model.order;

/**
 *
 * @author Administrator
 */
public class Check extends Payment {
    String name;
    String bankID;

    public Check() {
    }

    public Check(String name, String bankID, float totalPrice, String type) {
        super(totalPrice, type);
        this.name = name;
        this.bankID = bankID;
    }

    public Check(String name, String bankID, int id, float totalPrice, String type) {
        super(id, totalPrice, type);
        this.name = name;
        this.bankID = bankID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
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
        return "Check{" + "name=" + name + ", bankID=" + bankID + '}';
    }
    
    
}
