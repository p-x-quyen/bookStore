package web.book;

import dao.bookDAO.AuthorDAOImpl;
import dao.bookDAO.BookDAO;
import dao.bookDAO.BookDAOImpl;
import dao.bookDAO.BookItemDAO;
import dao.bookDAO.BookItemDAOImpl;
import dao.bookDAO.PublisherDAOImpl;
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
public class BookItemUpdate extends HttpServlet {

    private BookItemDAO bookItemDAO;
    
    @Override
    public void init() {
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
                Iterator<FileItem> iter1 = items.iterator();
                float price = 0;
                String discount = "0";
                String bookItemId = "0";
                while (iter.hasNext()) {
                    FileItem item = iter.next();

                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString();
                        if (name.equalsIgnoreCase("price")) {
                            price = Float.parseFloat(value);
                            System.out.println(value);
                        } else if (name.equalsIgnoreCase("discount")) {
                            if (!"".equalsIgnoreCase(value.trim())) {
                                discount = value.trim();
                                System.out.println(value);
                            } 
                        } else if (name.equalsIgnoreCase("bookItemId")) {
                            bookItemId = value.trim();
                            System.out.println(value);
                        }
                    }
                }
                
                if (!"0".equalsIgnoreCase(bookItemId)) {
                    BookItem bookItem = bookItemDAO.getBookItemById(Integer.parseInt(bookItemId));
                    int bookId = bookItem.getBook().getId();
                    while (iter1.hasNext()) {
                        FileItem item = iter1.next();

                        if (!item.isFormField()) {
                            String fileName =  item.getName();
                            System.out.println(fileName);
                            if (fileName == null || fileName.equalsIgnoreCase("")) {
    //                            System.out.println("img null");
                                break;
                            } else {
                                String storePath = servletContext.getRealPath("/uploads");
                                Path path = Paths.get(storePath + "/" + "img" + bookId + ".jpg");
                                Files.deleteIfExists(path);
                                File uploadFile = new File(storePath + "/" + "img" + bookId + ".jpg");
                                item.write(uploadFile);
                            }
                        } 
                    }
                    
                    BookItem bookItemUpdate = new BookItem(bookItem.getId(), "", price, discount, bookItem.getBook());
//                    System.out.println(bookItemUpdate.toString());
                    boolean result = bookItemDAO.updateBookItem(bookItemUpdate);
                    response.sendRedirect("BookItemDetails?id=" + bookItemId);
                }

            }
        } catch (FileUploadException ex) {
            Logger.getLogger(BookItemUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BookItemUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
