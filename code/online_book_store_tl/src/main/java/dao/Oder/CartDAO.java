package dao.Oder;

import model.order.Cart;

/**
 *
 * @author Administrator
 */
public interface CartDAO {
    boolean createCart(Cart cart, int orderId, int customerId);
    Cart getCartByOrderId(int orderId);
}
