package model.order;

/**
 *
 * @author Administrator
 */
public class Shipment {
    int id;
    String type;
    String code;
    String address;
    float price;

    public Shipment() {
    }

    public Shipment(String type, String code, String address, float price) {
        this.type = type;
        this.code = code;
        this.address = address;
        this.price = price;
    }

    public Shipment(int id, String type, String code, String address, float price) {
        this.id = id;
        this.type = type;
        this.code = code;
        this.address = address;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Shipment{" + "id=" + id + ", type=" + type + ", code=" + code + ", address=" + address + ", price=" + price + '}';
    }
    
    
}
