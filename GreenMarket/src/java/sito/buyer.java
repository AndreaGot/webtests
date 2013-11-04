/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sito;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mattia
 */
public class buyer extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String type_user = "";
            String user = "";
            if (session.getAttribute("user") != null || session.getAttribute("type_user") != null) {
                user = (String) session.getAttribute("user");
                type_user = (String) session.getAttribute("type_user");
            }
            if (type_user.equals("buyer")) {
                out.println("<html>");
                out.println("<head>"
                        + "<script type=\"text/javascript\" async=\"\" src=\"js/jquery-1.8.2.min.js\"></script>\n"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/logged.css\">\n"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/greenMarket.css\">"
                        + "<script type=\"text/javascript\" async=\"\" src=\"js/greenmarket.js\"></script>");
                out.println("<title>Servlet buyer</title>");
                out.println("</head>");
                out.println("<body>\n"
                        + "<img src=\"img/home/img1_.jpg\" id=\"sfondo1\" />\n"
                        + "<img src=\"img/home/img2_.jpg\" id=\"sfondo2\" />");
                out.println("<header><form method='post' id='carrello' name='carrello' action='carrello'><img onclick='$(\"#carrello\").submit();' src=\"img/cart.png\" class=\"account\" /></form><img src=\"img/user.png\" class=\"account\" /><img src=\"img/settings.png\" class=\"account\" /><p>Salve " + user + "</p><form method='post' action='logout'><input type='submit' id=\"logout\" value='Log-out' /></form></header>");
                out.println("<div id=\"container_log\">"
                        + "<form action=\"product_page\" method=\"post\" id=\"form_product\" name=\"form_product\">"
                        + "<input type=\"hidden\" value=\"\" id=\"id_product\" name=\"id_product\" />"
                        + "<div id=\"vini\"     class=\"offerte\" onclick=\"select_product(this.id)\"  ></div>"
                        + "<div id=\"formaggi\" class=\"offerte\" onclick=\"select_product(this.id)\"  ></div>"
                        + "<div id=\"pane\"     class=\"offerte\" onclick=\"select_product(this.id)\"  ></div>"
                        + "<div id=\"ortaggi\"  class=\"offerte\" onclick=\"select_product(this.id)\"  ></div>"
                        + "</form>"
                        + "</div>");
                out.println("<footer>\n"
                        + "<div id=\"footer\"></div>\n"
                        + "<p>&copy; Fravezzi Mattia 135759 - Tait Luca 145890 - Universita degli studi di Trento </p>\n"
                        + "</footer>");
                out.println("</body>");
                out.println("</html>");
            } else {
                response.sendRedirect("error");
            }
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
