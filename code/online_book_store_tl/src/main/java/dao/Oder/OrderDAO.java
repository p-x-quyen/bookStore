package dao.Oder;

import model.order.Order;

/**
 *
 * @author Administrator
 */
public interface OrderDAO {
    boolean createOrder(Order order, int customerId);
    Order getOrderById(int orderId,  int customerId);
}
