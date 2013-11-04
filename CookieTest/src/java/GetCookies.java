/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;

/** Creates a table of the cookies associated with the current page. */

public class GetCookies extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Active Cookies";
        out.println(("<HTML><HEAD><TITLE>" +title+ "</TITLE></HEAD>" + "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" + "<TABLE BORDER=1 ALIGN=\"CENTER\">\n" + "<TR BGCOLOR=\"#FFAD00\">\n" + "<TH>Cookie Name\n" + " <TH>Cookie Value"));
 
        Cookie[] cookies = request.getCookies();
        Cookie cookie;

        for(int i=0; i<cookies.length; i++) {
            cookie = cookies[i];
            out.println("<TR>\n" + " <TD>" + cookie.getName() + "\n" + " <TD>" + cookie.getValue());
        }
        
        out.println("</TABLE></BODY></HTML>");

 

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
