/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sito;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mattia
 */
public class error extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/nologged.css\">\n"
                    + "<script type=\"text/javascript\" async=\"\" src=\"js/greenmarket.js\"></script>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/greenMarket.css\">");
            out.println("<title>Servlet error</title>");
            out.println("</head>");
            out.println("<body id='page_error'>"
                    + "<img src=\"img/home/img1_.jpg\" id=\"sfondo1\" />\n"
                    + "<img src=\"img/home/img2_.jpg\" id=\"sfondo2\" />"
                    + "<header><img src='img/logo_error.png' alt='error' valign='middle'/>"
                    + "<h3 style='color:white;float:right;margin-right:100px'>Error 42: Access Danied, non hai i permessi per accedere a questa pagina.. "
                    + "<a href='./' style='color:green'>Torna alla HomePage e Loggati!!!</a> </h3></header>"
                    + "<div id=\"container\">");
            out.println("</div><footer>\n"
                    + "<div id=\"footer\"></div>\n"
                    + "<p>&copy; Fravezzi Mattia 135759 - Tait Luca 145890 - Universita degli studi di Trento </p>\n"
                    + "</footer>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
