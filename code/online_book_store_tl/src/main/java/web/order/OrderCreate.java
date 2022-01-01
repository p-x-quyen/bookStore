package web.order;

import dao.OrderDAO.CartDAO;
import dao.OrderDAO.CartDAOImpl;
import dao.OrderDAO.OrderDAO;
import dao.OrderDAO.OrderDAOImpl;
import dao.bookDAO.BookItemDAO;
import dao.bookDAO.BookItemDAOImpl;
import dao.customerDAO.CustomerDAO;
import dao.customerDAO.CustomerDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.order.Cart;
import model.order.Check;
import model.order.Order;
import model.order.Shipment;

/**
 *
 * @author Administrator
 */
public class OrderCreate extends HttpServlet {

    private CartDAO carDAO;
    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;
    
    @Override
    public void init() {
        this.carDAO = new CartDAOImpl();
        this.customerDAO = new CustomerDAOImpl();
        this.orderDAO = new OrderDAOImpl();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("viewCart")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("customer/cart-details.jsp");
                dispatcher.forward(request, response);
            } else if (action.equalsIgnoreCase("create")) {
                HttpSession httpSession = request.getSession(false);
                Cart cart = (Cart)httpSession.getAttribute("cart");
                int customerId = customerDAO.getIdCustomerByUsername((String) httpSession.getAttribute("username"));
                
                if (cart.getTotalQuantity() == 0 ) {
                    out.println("your cart does not has any book");
                } else if (customerId == 0) {
                    out.println("please login to order");
                } else {
                    String shipmentType = request.getParameter("shipmentType");
//                    System.out.println(shipmentType);
                    String shipmentAddress = request.getParameter("shipmentAddress");
//                    System.out.println(shipmentAddress);

                    Shipment shipment = new Shipment();
                    shipment.setAddress(shipmentAddress);
                    shipment.setType(shipmentType);
                    shipment.setCode("none");
                    shipment.setPrice(30000);
                    String paymentType = request.getParameter("paymentType");
//                    System.out.println(paymentType);
                    
                    if (paymentType.equalsIgnoreCase("check")) {
                        String name = request.getParameter("name");
//                        System.out.println(name);
                        String bankId = request.getParameter("bankId");
//                        System.out.println(bankId);
                        Check check = new Check();
                        check.setTotalPrice(shipment.getPrice() + cart.getTotalPrice());
                        check.setType("check");
                        check.setBankID(bankId);
                        check.setName(name);
                       
                        Order order = new Order(new Date(), "0", cart);
                        boolean result = orderDAO.createOrder(order, customerId, shipment, check);
                        if (result == true) {
                            httpSession = request.getSession(true);
                            httpSession.setAttribute("cart", new Cart());
                        }
                        
                        out.println((result) ? "successful" : "fail");
                    }
                    
                }
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
