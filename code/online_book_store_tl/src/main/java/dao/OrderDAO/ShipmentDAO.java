package dao.OrderDAO;

import model.order.Shipment;

/**
 *
 * @author Administrator
 */
public interface ShipmentDAO {
    int createShipment(Shipment shipment, int orderId);
    Shipment getShipmentByOrderId(int orderId);
}
