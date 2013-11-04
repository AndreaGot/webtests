/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author ANDre1
 */
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;

/** Sets six cookies: three that apply only to the current session 

 * (regardless of how long that session lasts) and three that persist for an hour 

 * (regardless of whether the browser is restarted).

 */

public class SetCookies extends HttpServlet {

 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     for(int i=0; i<5; i++) {
         
         // Crea cookie che vengono cancellati alla fine della sessione browser

         Cookie cookie = new Cookie("Session-Cookie-" + i, "Cookie-Value-S" + i);
         cookie.setMaxAge(-1);
         response.addCookie(cookie);
           
         // Crea cookie che vengono cancellati dopo un'ora, indipendentemente da ciò che l'utente fa nel frattempo
       
         cookie = new Cookie("Persistent-Cookie-" + i,"Cookie-Value-P" + i);
         cookie.setMaxAge(3600);
         response.addCookie(cookie);
     } 

     response.setContentType("text/html");
     PrintWriter out = response.getWriter();
 
     String title = "Setting Cookies";
     out.println (("<HTML><HEAD><TITLE>" +title+ "</TITLE></HEAD>" + "<BODY BGCOLOR=\"#FDF5E6\">\n" +"<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +"There are six cookies associated with this page.\n" +"</BODY></HTML>"));

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
