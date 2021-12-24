package web;

import dao.customerDAO.CustomerDAO;
import dao.customerDAO.CustomerDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customer.Account;
import model.order.Cart;

/**
 *
 * @author Administrator
 */
public class Login extends HttpServlet {

    private CustomerDAO customerDAO;
    
    @Override
    public void init() {
        this.customerDAO = new CustomerDAOImpl();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            if (request.getParameter("username") == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response); 
                return;
            } 
            
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();
            
            if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                response.sendRedirect("BookList");
                
            } else {
                Account account = new Account(username, password);
                Account result = customerDAO.login(account);
                if (result.getId() != 0) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("username", username);
                    session.setAttribute("cart", new Cart());
                    response.sendRedirect("BookItemList");
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Password incorrect');");
                    out.println("location='index.jsp';");
                    out.println("</script>");
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
