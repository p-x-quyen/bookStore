package web.order;

import dao.Oder.CartDAO;
import dao.Oder.CartDAOImpl;
import dao.Oder.OrderDAO;
import dao.Oder.OrderDAOImpl;
import dao.Oder.PaymentDAO;
import dao.Oder.PaymentDAOImpl;
import dao.Oder.ShipmentDAO;
import dao.Oder.ShipmentDAOImpl;
import dao.customerDAO.CustomerDAO;
import dao.customerDAO.CustomerDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Pair;
import model.book.BookItem;
import model.order.Cart;
import model.order.Check;
import model.order.Order;
import model.order.Payment;
import model.order.Shipment;

/**
 *
 * @author Administrator
 */
public class OrderDetails extends HttpServlet {

    private CartDAO carDAO;
    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;
    private ShipmentDAO shipmentDAO;
    private PaymentDAO paymentDAO;
    
    @Override
    public void init() {
        this.carDAO = new CartDAOImpl();
        this.customerDAO = new CustomerDAOImpl();
        this.orderDAO = new OrderDAOImpl();
        this.shipmentDAO = new ShipmentDAOImpl();
        this.paymentDAO = new PaymentDAOImpl();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpSession httpSession = request.getSession(false);
            String username = (String)httpSession.getAttribute("username");
            int customerId = customerDAO.getIdCustomerByUsername(username);
            if (request.getParameter("id") == null && customerId != 0) {
                List<Order> listOrders = orderDAO.getAllOrdersByCustomerId(customerId);
                request.setAttribute("listOrders", listOrders);
                RequestDispatcher dispatcher = request.getRequestDispatcher("customer/orders.jsp");
                dispatcher.forward(request, response);
            } else if (customerId != 0) {
                int orderId = Integer.parseInt(request.getParameter("id"));
                Order order = orderDAO.getOrderById(orderId);
                Shipment shipment = shipmentDAO.getShipmentByOrderId(orderId);
                Payment payment = paymentDAO.getPaymentByOrderId(orderId);
                
                String html =   "<div>\n" +
                                "<table class=\"table table-bordered table-striped mb-2 book-table\">\n" +
                                "    <thead>\n" +
                                "        <tr>\n" +
                                "            <th class=\"border-bottom-0\">\n" +
                                "                Image\n" +
                                "            </th>\n" +
                                "            <th class=\"border-bottom-0\">\n" +
                                "                Name\n" +
                                "            </th>\n" +
                                "            <th class=\"border-bottom-0\">\n" +
                                "                Price\n" +
                                "            </th>\n" +
                                "            <th class=\"border-bottom-0\">\n" +
                                "                Quantity\n" +
                                "            </th>\n" +
                                "            <th class=\"border-bottom-0\">\n" +
                                "                Discount\n" +
                                "            </th>\n" +
                                "        </tr>\n" +
                                "    </thead>\n" +
                                "    <tbody>";
                
                Cart cart = order.getCart();
                List<Pair<BookItem, Integer>> listBookItems = cart.getListBookItems();
                
                for (Pair<BookItem, Integer> bookItemPair: listBookItems) {
                    html += "<tr>\n" +
                    "            <td class=\"align-middle\">\n" +
                    "                <img src=\"" + request.getContextPath() + "/bookItemImages/" + bookItemPair.getKey().getImage() + "\" style=\"display:block; width: 200px; height:250px;\">\n" +
                    "            </td>\n" +
                    "            <td class=\"align-middle\">\n" +
                    "                <a href=\"BookItemDetails?id=" + bookItemPair.getKey().getId() + "\" class=\"text-dark\">\n" +
                                        bookItemPair.getKey().getBook().getName() + "\n" +
                    "                </a>\n" +
                    "            </td>\n" +
                    "            <td class=\"align-middle\">\n" +
                                        bookItemPair.getKey().getPrice() + "\n" +
                    "            </td>\n" +
                    "            <td class=\"align-middle\">\n" +
                                        bookItemPair.getValue() + "\n" +
                    "            </td>\n" +
                    "            <td class=\"align-middle\">\n" +
                                        bookItemPair.getKey().getDiscount() + "\n" +
                    "            </td>\n" +
                    "        </tr>";
                }
                
                html += "<tr>\n" +
                    "            <td class=\"align-middle\">\n" +
                    "                Total of books\n" +
                    "            </td>\n" +
                    "            <td class=\"align-middle\">\n" +
                    "            </td>\n" +
                    "            <td class=\"align-middle\">\n" +
                                        cart.getTotalPrice() + "\n" +
                    "            </td>\n" +
                    "            <td class=\"align-middle\">\n" +
                                        cart.getTotalQuantity()+ "\n" +
                    "            </td>\n" +
                    "            <td class=\"align-middle\">\n" +
                    "            </td>\n" +
                    "        </tr>\n" +
                    "    </tbody>\n" +
                    "</table>\n" +
                    "</div>";
                
                html += "<div class=\"shadow p-3 mb-3 mt-3 bg-white rounded\">\n" +
                        "    <h5>Shipment</h5>\n" +
                        "    <div class=\"form-group d-flex flex-column\">\n" +
                        "        <label for=\"shipment-type\">Type</label>\n" +
                        "        <input type=\"text\" class=\"form-control\" id=\"shipment-type\" value=\"" + shipment.getType() + "-" + shipment.getPrice() + "\" disabled>\n" +
                        "    </div>\n" +
                        "    <div class=\"form-group\">\n" +
                        "        <label for=\"shipment-address\">Address</label>\n" +
                        "        <input type=\"text\" class=\"form-control\" id=\"shipment-address\" value=\"" + shipment.getAddress() + "\" disabled>\n" +
                        "    </div>\n" +
                        "</div>";
                

                html += "<div class=\"shadow p-3 mb-3 mt-3 bg-white rounded\">\n" +
                        "<h5>Check out</h5>\n" +
                        "<div class=\"form-group d-flex flex-column\">\n" +
                        "    <label for=\"check-out-type\">Type</label>\n" +
                        "    <input type=\"text\" class=\"form-control\" id=\"check-out-type\" value=\"" + payment.getType() + "\" disabled>\n" +
                        "</div>\n" +
                        "<div class=\"form-group d-flex flex-column\">\n" +
                        "    <label for=\"check-out-total\">Total</label>\n" +
                        "    <input type=\"text\" class=\"form-control\" id=\"check-out-total\" value=\"" + payment.getTotalPrice() + "\" disabled>\n" +
                        "</div>\n" +
                        "</div>";
                
                out.println(html);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
