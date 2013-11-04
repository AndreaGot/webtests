/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sito;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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

public class add_product extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ArrayList categories = null;
        db.connect_mysql my_connect;
        my_connect = new db.connect_mysql();
        try {
            HttpSession session = request.getSession();
            String type_user = "";
            String user = "";

            if (session.getAttribute("user") != null || session.getAttribute("type_user") != null) {
                user = (String) session.getAttribute("user");
                type_user = (String) session.getAttribute("type_user");
            }
            if (type_user.equals("seller")) {
                out.println("<html>");
                out.println("<head>"
                        + "<script type=\"text/javascript\" async=\"\" src=\"js/jquery-1.8.2.min.js\"></script>\n"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/logged.css\">\n"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/greenMarket.css\">"
                        + "<script type=\"text/javascript\" async=\"\" src=\"js/greenmarket.js\"></script>");
                out.println("<title>Servlet add_product</title>");
                out.println("</head>");
                out.println("<body>\n"
                        + "<img src=\"img/home/img1_.jpg\" id=\"sfondo1\" />\n"
                        + "<img src=\"img/home/img2_.jpg\" id=\"sfondo2\" />");
                out.println("<header></form><img src=\"img/user.png\" class=\"account\" /><img src=\"img/settings.png\" class=\"account\" /><p>Salve " + user + "</p><form method='post' action='logout'><input type='submit' id=\"logout\" value='Log-out' /></form></header>");
                out.println("<div id=\"container_spesa\">"
                        + "<form action=\"submit_product\" method=\"post\" id=\"form_product\" name=\"form_product\">");

                categories = my_connect.get_categories();
                Iterator itr_list_products = categories.iterator();
                int i = 0;
                out.println("<table id='products' CELLSPACING='0' BORDER='0' >");

                out.println("<tr class='row2'><td>Categoria</td><td>");
                out.println("<select id='category' name='category'>");
                while (itr_list_products.hasNext()) {
                    String[] cat = (String[]) itr_list_products.next();
                    out.println("<option value=" + cat[0] + " >" + cat[1] + "</option>");
                }
                out.println("</select>");
                out.println("</td></tr>");
                out.println("<tr class='row1'><td>Nome</td><td><input type='text' value='' id='name' name='name'></td></tr>");
                out.println("<tr class='row2'><td>Quantità</td><td><input type='text' value='' id='quantity' name='quantity'></td></tr>");
                out.println("<tr class='row1'><td>Unità di misura</td><td><input type='text' value='' id='um' name='um'></td></tr>");
                out.println("<tr class='row2'><td>Prezzo</td><td><input type='text' value='' id='price' name='price'></td></tr>");
                out.println("<tr><td colspan='7' style='text-align:right'><input type='submit' value='Aggiungi prodotto' id='Compra' name='Aggiungi Prodotto' /></td></td></tr>");
                out.println("</table>");
                out.println("</form></div>");
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(add_product.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_product.class.getName()).log(Level.SEVERE, null, ex);
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
