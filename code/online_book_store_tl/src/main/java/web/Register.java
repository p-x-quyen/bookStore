package web;

import dao.customerDAO.CustomerDAO;
import dao.customerDAO.CustomerDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.customer.Account;
import model.customer.Address;
import model.customer.CustomerNew;
import model.customer.FullName;

/**
 *
 * @author Administrator
 */
public class Register extends HttpServlet {

    private CustomerDAO customerDAO;
    
    @Override
    public void init() {
        this.customerDAO = new CustomerDAOImpl();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (request.getParameter("username") == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.forward(request, response);   
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");  
                String firstName = request.getParameter("firstName");
                String middleName = request.getParameter("middleName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                Date dateOfBirth = formatter.parse(request.getParameter("dateOfBirth"));
                String gender = request.getParameter("gender");
                String city = request.getParameter("city");
                String district = request.getParameter("district");
                String street = request.getParameter("street");
                String houseNumber = request.getParameter("houseNumber");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                
                System.out.println(firstName);
                System.out.println(middleName);
                System.out.println(lastName);
                System.out.println(email);
                System.out.println(phone);
                System.out.println(dateOfBirth);
                System.out.println(gender);
                System.out.println(city);
                System.out.println(district);
                System.out.println(street);
                System.out.println(houseNumber);
                System.out.println(username);
                System.out.println(password);
                
                String note = "";
                String type = "new";
                
                if (!username.matches("^[a-z0-9_-]{3,}$")) {
                    out.println("username: has more then 2 characters with any lower case character, digit or special symbol '_' only");
                    return;
                }
                
                if (username.contains("admin")) {
                    out.println("username is not allowed to contain 'admin'");
                    return;
                }
                
                if (password.contains("admin")) {
                    out.println("passowrd is not allowed to contain 'admin'");
                    return;
                }
                
                if (!phone.matches("^(\\d{3}[- .]?){2}\\d{4}$")) {
                    out.println("phone number pattern must be like this : 202 555 0125");
                    return;
                }
                
                if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
                                 + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
                    out.println("invalid email");
                    return;
                }
                
                Account account = new Account(username, password);
                Address address = new Address(city, district, street, houseNumber);
                FullName fullName = new FullName(firstName, middleName, lastName);
                CustomerNew customerNew = new CustomerNew(note, email, phone, dateOfBirth, type, gender, account, address, fullName);
                
                System.out.println(customerNew.getAccount().toString());
                String result = customerDAO.createCustomer(customerNew);
                out.println(result);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
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
