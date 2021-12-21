package dao.customerDAO;

import model.customer.Account;
import model.customer.CustomerNew;

/**
 *
 * @author Administrator
 */
public interface CustomerDAO {
    String createCustomer(CustomerNew customerNew);
    Account login(Account account);
}
