package dao.OrderDAO;

import model.order.Payment;

/**
 *
 * @author Administrator
 */
public interface PaymentDAO {
    boolean createPayment(Object object, int orderId, int shipmentId);
    Payment getPaymentByOrderId(int orderId);
}
