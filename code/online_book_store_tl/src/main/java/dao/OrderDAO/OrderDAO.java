package dao.OrderDAO;

import java.util.List;
import model.order.Order;
import model.order.Shipment;

/**
 *
 * @author Administrator
 */
public interface OrderDAO {
    boolean createOrder(Order order, int customerId, Shipment shipment, Object object);
    Order getOrderById(int orderId);
    List<Order> getAllOrdersByCustomerId(int customerId);
}
