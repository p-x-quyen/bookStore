package web.book;

import dao.bookDAO.BookDAO;
import dao.bookDAO.BookDAOImpl;
import dao.bookDAO.BookItemDAO;
import dao.bookDAO.BookItemDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.book.Book;
import model.book.BookItem;

/**
 *
 * @author Administrator
 */
public class BookItemDetails extends HttpServlet {

    private BookDAO bookDAO;
    private BookItemDAO bookItemDAO;
    
    @Override
    public void init() {
        this.bookDAO = new BookDAOImpl();
        this.bookItemDAO = new BookItemDAOImpl();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpSession httpSession = request.getSession(false);
            String username = (String)httpSession.getAttribute("username");
            
            if (username.equalsIgnoreCase("admin") && request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                BookItem bookItem = bookItemDAO.getBookItemById(id);
                request.setAttribute("bookItem", bookItem);
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin/book-item-details.jsp");
                dispatcher.forward(request, response);   
            } else {
                int id = Integer.parseInt(request.getParameter("id"));
                BookItem bookItem = bookItemDAO.getBookItemById(id);
                request.setAttribute("bookItem", bookItem);
                RequestDispatcher dispatcher = request.getRequestDispatcher("customer/book-item-details.jsp");
                dispatcher.forward(request, response);
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
