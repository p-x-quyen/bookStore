package web.search;

import dao.bookDAO.AuthorDAO;
import dao.bookDAO.AuthorDAOImpl;
import dao.bookDAO.BookDAO;
import dao.bookDAO.BookDAOImpl;
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

/**
 *
 * @author Administrator
 */
public class Search extends HttpServlet {
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    
    @Override
    public void init() {
        this.bookDAO = new BookDAOImpl();
        this.authorDAO = new AuthorDAOImpl();
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
                        searchBook(value, out, request, response);
                        break;
                    case "author":
                        searchAuthor(value, out, request, response);
                }
            }
        }
    }
    
    private void searchBook(String value, PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
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
    
    private void searchAuthor(String value, PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
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