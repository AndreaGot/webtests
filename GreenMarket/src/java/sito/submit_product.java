/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sito;

import db.product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luca
 */
public class submit_product extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        logs mylog = null;
        mylog = new logs();
        db.connect_mysql my_connect;
        my_connect = new db.connect_mysql();
        try
        {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        String name = request.getParameter("name");
        String id_user = my_connect.get_id_from_user(user);
        String quantity = request.getParameter("quantity");
        String category = request.getParameter("category");
        String um = request.getParameter("um");
        String price = request.getParameter("price");
        String image = "img/products/"+name+".jpg";
        
        String Query = "INSERT INTO products (name,id_user,quantity,category,UM,price,date,image) VALUES ('"+name+"',"+id_user+","+quantity+","+category+",'"+um+"',"+price+",NOW(),'"+image+"')"; 
        my_connect.Insert(Query);
       
        response.sendRedirect("seller");
        } catch(Exception e){
            mylog.write(e.getMessage());}
        finally
        {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(submit_product.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(submit_product.class.getName()).log(Level.SEVERE, null, ex);
        }
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
