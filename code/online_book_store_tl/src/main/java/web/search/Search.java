package web.search;

import dao.bookDAO.AuthorDAO;
import dao.bookDAO.AuthorDAOImpl;
import dao.bookDAO.BookDAO;
import dao.bookDAO.BookDAOImpl;
import dao.bookDAO.BookItemDAO;
import dao.bookDAO.BookItemDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.book.Author;
import model.book.Book;
import model.book.BookItem;

/**
 *
 * @author Administrator
 */
public class Search extends HttpServlet {
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    private BookItemDAO bookItemDAO;
    
    @Override
    public void init() {
        this.bookDAO = new BookDAOImpl();
        this.authorDAO = new AuthorDAOImpl();
        this.bookItemDAO = new BookItemDAOImpl();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpSession httpSession = request.getSession(false);
            String username = (String)httpSession.getAttribute("username");
            
            if (username.equalsIgnoreCase("admin")) {
                String object = request.getParameter("object");
                String value = request.getParameter("value");
                switch(object) {
                    case "book":
                        searchBookForAdmin(value, out, request, response);
                        break;
                    case "author":
                        searchAuthorForAdmin(value, out, request, response);
                        break;
                    case "bookItem":
                        searchBookItemForAdmin(value, out, request, response);
                        break;
                }
            } else {
                String object = request.getParameter("object");
                String value = request.getParameter("value");
                switch(object) {
                    case "bookItem":
                        searchBookItemForCustomer(value, out, request, response);
                        break;
                }
            }
        }
    }
    
    private void searchBookForAdmin(String value, PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        List<Book> listBooks = bookDAO.searchBookByName(value);
        for(Book book: listBooks) {
            out.println("<tr>\n" +
                        "<td class=\"align-middle\">\n" +
                        book.getId() + "\n" +
                        "</td>\n" +
                        "<td class=\"align-middle\">\n" +
                        "<a href=\"BookDetails?id=" + book.getId() + "\" class=\"text-dark\">\n" +
                        book.getName()+ "\n" +
                        "</a>\n" +
                        "</td>\n" +
                        "<td class=\"align-middle\">\n" +
                        book.getIsbn() + "\n" +
                        "</td>\n" +
                        "<td class=\"align-middle\">\n" +
                        book.getLanguage() + "\n" +
                        "</td>\n" +
                        "<td class=\"align-middle\">\n" +
                        book.getPublisher().getName() + "\n" +
                        "</td>\n" +
                        "</tr>");
        }
    }
    
    private void searchAuthorForAdmin(String value, PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        List<Author> listAuthors = null;
        if (value.equalsIgnoreCase("")) {
            listAuthors = authorDAO.getAllAuthors();
        } else {
            listAuthors = authorDAO.searchAuthorByName(value);
        }
        for (Author author: listAuthors) {
            out.println("<tr>\n" +
                        "<td class=\"align-middle id-search-result\">\n" +
                        author.getId() + "\n" +
                        "</td>\n" +
                        "<td class=\"align-middle full-name-search-result\">\n" +
                        author.getFullName()+ "\n" +
                        "</td>\n" +
                        "<td class=\"align-middle address-search-result\">\n" +
                        author.getAddress()+ "\n" +
                        "</td>\n" +
                        "<td class=\"align-middle\">\n" +
                        "<button type=\"button\" class=\"btn-primary px-3 border-0 rounded btn-add-author\" style=\"box-shadow: none!important;\">\n" +
                        "Add\n" +
                        "</button>\n" +
                        "</td>\n" +
                        "</tr>");
        }
    }
    
    private void searchBookItemForAdmin(String value, PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        List<BookItem> listBookItems = bookItemDAO.searchBookItemByName(value);
        for(BookItem bookItem: listBookItems) {
            out.println("<tr>\n" +
                        "<td class=\"align-middle\">\n" +
                        bookItem.getId() + "\n" +
                        "</td>\n" +
                        "<td class=\"align-middle\">\n" +
                        "<a href=\"BookItemDetails?id=" + bookItem.getId() + "\" class=\"text-dark\">\n" +
                        bookItem.getBook().getName() + "\n" +
                        "</a>\n" +
                        "</td>\n" +
                        "<td class=\"align-middle\">\n" +
                        "<img src=\"" + request.getContextPath() + "/bookItemImages/" + bookItem.getImage() + "\" class=\"book-item-img\">\n" +
                        "</td>\n" +
                        "<td class=\"align-middle\">\n" +
                        bookItem.getPrice() + "\n" +
                        "</td>\n" +
                        "</tr>");
        }
    }
    
    private void searchBookItemForCustomer(String value, PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        List<BookItem> listBookItems = bookItemDAO.searchBookItemByName(value);
        for(BookItem bookItem: listBookItems) {
            float discount = Float.parseFloat(bookItem.getDiscount().trim());
            float priceCurrent = bookItem.getPrice() - (bookItem.getPrice() * (discount / 100));
            out.println("<div class=\"col col-sm-4\">\n" +
                        "<a class=\"book-item\" href=\"BookItemDetails?id=" + bookItem.getId() + "\">\n" +
                        "<div class=\"book-item__img\" style=\"background-image: url(" + request.getContextPath() + "/bookItemImages/" + bookItem.getImage() + ")\"></div>\n" +
                        "<h4 class=\"book-item__name\">\n" +
                        bookItem.getBook().getName() + "\n" +
                        "</h4>\n" +
                        "<div class=\"book-item__price d-flex flex-column\">\n" +
                        "<span class=\"book-item__price-old \">" + bookItem.getPrice() + " (VND)</span>\n" +
                        "<span class=\"book-item__price-current\">" + priceCurrent + " (VND)</span>\n" +
                        "</div>\n" +
                        "<div class=\"book-item__origin\">\n" +
                        "<span class=\"book-item__brand \">Book store</span>\n" +
                        "<span class=\"book-item__origin-name \">Ba Đình - Hà Nội</span>\n" +
                        "</div>\n" +
                        "<div class=\"book-item__favourite\">\n" +
                        "<i class=\"fas fa-check\"></i>\n" +
                        "<span>Favorite</span>\n" +
                        "</div>\n" +
                        "<div class=\"book-item__sale-off\">\n" +
                        "<span class=\"book-item__sale-off-label\">Off</span>\n" +
                        "<span class=\"book-item__sale-off-percent\">" + bookItem.getDiscount() + "%</span>\n" +
                        "</div>\n" +
                        "</a>\n" +
                        "</div>");
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