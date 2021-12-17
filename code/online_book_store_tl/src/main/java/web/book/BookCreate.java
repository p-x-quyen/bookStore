package web.book;

import dao.bookDAO.AuthorDAO;
import dao.bookDAO.AuthorDAOImpl;
import dao.bookDAO.BookDAO;
import dao.bookDAO.BookDAOImpl;
import dao.bookDAO.PublisherDAO;
import dao.bookDAO.PublisherDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.book.Author;
import model.book.Book;
import model.book.Publisher;

/**
 *
 * @author Administrator
 */
public class BookCreate extends HttpServlet {

    private AuthorDAO authorDAO;
    private PublisherDAO publisherDAO;
    private BookDAO bookDAO;
    
    @Override
    public void init() {
        this.authorDAO = new AuthorDAOImpl();
        this.publisherDAO = new PublisherDAOImpl();
        this.bookDAO = new BookDAOImpl();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpSession httpSession = request.getSession(false);
            String username = (String)httpSession.getAttribute("username");
            
            if (username.equalsIgnoreCase("admin")) {
                String action = request.getParameter("action");
                if (action.equalsIgnoreCase("view")) {
                    List<Publisher> listPublishers = publisherDAO.getAllPublishers();
                    request.setAttribute("listPublishers", listPublishers);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("admin/book-create.jsp");
                    dispatcher.forward(request, response);
                } else if (action.equalsIgnoreCase("create")) {
                    String bookName = request.getParameter("bookName");
                    String bookSummary = request.getParameter("bookSummary");
                    int numberOfPages;
                    try {
                        numberOfPages = Integer.parseInt(request.getParameter("numberOfPages"));
                    } catch (NumberFormatException e) {
                        out.println("wrong format: number of pages");
                        return;
                    }
                    String bookLanguage = request.getParameter("bookLanguage");
                    String isbn = request.getParameter("isbn");
                    String[] listAuthorIds = request.getParameter("listAuthorIds").split(";");
                    int publisherId;
                    try {
                        publisherId = Integer.parseInt(request.getParameter("publisherId"));
                    } catch (NumberFormatException e) {
                        out.println("wrong format: publisher id - " + request.getParameter("publisherId"));
                        return;
                    }
                    
//                    System.out.println(bookName);
//                    System.out.println(bookSummary);
//                    System.out.println(numberOfPages);
//                    System.out.println(bookLanguage);
//                    System.out.println(isbn);
//                    System.out.println(Arrays.toString(listAuthors));
//                    System.out.println(publisher);
                    
                    List<Author> listAuthors = new ArrayList<>();
                    for (int i = 0; i < listAuthorIds.length; i++) {
                        int authorId;
                        try {
                            authorId = Integer.parseInt(listAuthorIds[i]);
                            Author author = authorDAO.getAuthorById(authorId);
                            if (author.getId() == 0) {
                                out.println("cannot find: author id - " + authorId);
                                return;
                            } 
                            listAuthors.add(author);
                        } catch (NumberFormatException e) {
                            out.println("wrong format: author id - " + listAuthorIds[i]);
                            return;
                        } 
                    }
                    
                    Publisher publisher = publisherDAO.getPublisherById(publisherId);
                    if (publisher.getId() == 0) {
                        out.println("cannot find: publisher id - " + publisherId);
                        return;
                    }
                    
                    Book book = new Book(bookName, bookSummary, numberOfPages, bookLanguage, isbn, listAuthors, publisher);
                    boolean result = bookDAO.createBook(book);
                    out.println(result == true ? "success" : "error");
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
