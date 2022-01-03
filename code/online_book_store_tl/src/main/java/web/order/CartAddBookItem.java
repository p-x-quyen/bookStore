package web.order;

import dao.bookDAO.BookItemDAO;
import dao.bookDAO.BookItemDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Pair;
import model.book.Book;
import model.book.BookItem;
import model.order.Cart;

/**
 *
 * @author Administrator
 */
public class CartAddBookItem extends HttpServlet {

    private BookItemDAO bookItemDAO;
    
    @Override
    public void init() {
        this.bookItemDAO = new BookItemDAOImpl();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession httpSession = request.getSession(false);
            Cart cart = (Cart)httpSession.getAttribute("cart");
            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("add")) {
                int id = Integer.parseInt(request.getParameter("bookItemId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                BookItem bookItem = bookItemDAO.getBookItemById(id);

                if (bookItem.getId() != 0) {
                    Pair<BookItem, Integer> bookItemPair = new Pair<>(bookItem, quantity);
                    cart.addBookItemPair(bookItemPair);

                    out.println("success (number of books: " + cart.getTotalQuantity() + ")");
                } else {
                    out.println("cannot add to cart");
                }
            } else if (action.equalsIgnoreCase("remove")) {
                int id = Integer.parseInt(request.getParameter("bookItemId"));
                cart.removeBookItemPair(id);
                response.sendRedirect("OrderCreate?action=viewCart");
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
