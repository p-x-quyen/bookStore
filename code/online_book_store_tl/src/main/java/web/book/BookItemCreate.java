package web.book;

import dao.bookDAO.BookDAO;
import dao.bookDAO.BookDAOImpl;
import dao.bookDAO.BookItemDAO;
import dao.bookDAO.BookItemDAOImpl;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.book.Book;
import model.book.BookItem;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Administrator
 */
public class BookItemCreate extends HttpServlet {

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
            
            if (username.equalsIgnoreCase("admin")) {
                // Create a factory for disk-based file items
                DiskFileItemFactory factory = new DiskFileItemFactory();

                // Configure a repository (to ensure a secure temp location is used)
                ServletContext servletContext = this.getServletConfig().getServletContext();
                File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                factory.setRepository(repository);

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);

                // Parse the request
                List<FileItem> items = upload.parseRequest(request);

                // Process the uploaded items
                Iterator<FileItem> iter = items.iterator();
                float price = 0;
                String discount = "0";
                String bookId = "0";
                while (iter.hasNext()) {
                    FileItem item = iter.next();

                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString();
                        if (name.equalsIgnoreCase("price")) {
                            price = Float.parseFloat(value);
//                            System.out.println(value);
                        } else if (name.equalsIgnoreCase("discount")) {
                            if (!"".equalsIgnoreCase(value.trim())) {
                                discount = value.trim();
//                                System.out.println(value);
                            } 
                        } else if (name.equalsIgnoreCase("bookId")) {
                            bookId = value.trim();
//                            System.out.println(value);
                        }
                    } else {
                        String fileName =  item.getName();
//                        System.out.println(fileName);
                        if (fileName == null || fileName.equalsIgnoreCase("")) {
                            break;
                        } else {
    //                        Path path = Paths.get(fileName);
                            String storePath = servletContext.getRealPath("/bookItemImages");
                            if (!Files.exists(Paths.get(storePath))) {
                                Files.createDirectory(Paths.get(storePath));
//                                System.out.println("created");
                            }
                            
                            File uploadFile = new File(storePath + "/" + "img" + bookId + ".jpg");
                            item.write(uploadFile);
                        }
                    }
                }

                if (price != 0 && !bookId.equalsIgnoreCase("0")) {
                    Book book = bookDAO.getBookById(Integer.parseInt(bookId.trim()));
                    BookItem bookItem = new BookItem("img" + bookId + ".jpg", price, discount, book);
                    boolean result = bookItemDAO.createBookItem(bookItem);
                    
                    response.sendRedirect("BookItemList");
                }
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(BookItemCreate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BookItemCreate.class.getName()).log(Level.SEVERE, null, ex);
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
