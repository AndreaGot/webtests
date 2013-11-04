/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.DBManager;
import db.User;
import db.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author ANDre1
 */
public class LoadProductsServlet extends HttpServlet {
private DBManager manager;
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServletzzz</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("Ciao, " + session.getAttribute("user"));
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
       try {

 // TODO: usare un Filter per controllare se esiste un utente collegato e/o la sessione è scaduta, altrimenti rimandare al login
            HttpSession session = req.getSession(false);
            List<Product> products = manager.getProducts();

                if (session == null || session.getAttribute("user") == null) {
                    resp.sendRedirect(req.getContextPath());
                } else {
                    //List<Product> products = manager.getProducts();
                    //session.setAttribute("products", products);
                      PrintWriter out = resp.getWriter();
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet NewServlet</title>");            
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1> prodotti caricati Servlet NewServlet at " + req.getContextPath() + "</h1>");
                        for (int i = 0; i < products.size(); i++) {
                            out.println( products.get(i).getName());
                            // 1 - can call methods of element
                            // 2 - can use i to make index-based calls to methods of list
                            
                            // ...
                        }
                        out.println("</body>");
                        out.println("</html>");
                }

                } 
                catch (Exception ex) {

                    Logger.getLogger(LoadProductsServlet.class.getName()).log(Level.SEVERE, ex.toString(), ex);

                throw new ServletException(ex);

                  }
        
        
        processRequest(req, resp);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         HttpSession session = request.getSession(false);
         try{
         if(session == null || session.getAttribute("user") == null) {
                    response.sendRedirect(request.getContextPath());
                } else {
                    List<Product> products = manager.getProducts();
                    session.setAttribute("products", products);
                    }
         }
          catch (SQLException ex) {

                    Logger.getLogger(LoadProductsServlet.class.getName()).log(Level.SEVERE, ex.toString(), ex);

        throw new ServletException(ex);
          }
       processRequest(request, response);
        //this.doGet(request, response);
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
