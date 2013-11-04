/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;

/**
 *
 * @author ANDre1
 */
public class DeleteCookies extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie cookie = null;
        Cookie[] cookies = null;
        
        // Get an array of Cookies associated with this domain
        
        cookies = request.getCookies();
        
        // Set response content type
        
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        String title = "Delete Cookies Example";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";

        out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n" );

        if( cookies != null ){

            out.println("<h2> Cookies Name and Value</h2>");
            
            for (int i = 0; i < cookies.length; i++){

                cookie = cookies[i];
                if((cookie.getName()).compareTo("first_name") == 0 ){

                    cookie.setMaxAge(0);
                    response.addCookie(cookie);

                    out.print("Deleted cookie : " + cookie.getName( ) + "<br/>");
                }
                
                out.print("Name : " + cookie.getName( ) + ", ");
                out.print("Value: " + cookie.getValue( )+" <br/>");
            }
        }else{
            out.println("<h2>No cookies founds</h2>");
        }
    }

    
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
            throws ServletException, IOException {}
    

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
