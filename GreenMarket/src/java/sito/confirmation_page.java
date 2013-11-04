/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sito;

import db.connect_mysql;
import db.product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mattia
 */
public class confirmation_page extends HttpServlet {

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
        String[] carrello = request.getParameterValues("carrello");
        db.connect_mysql my_connect;
        my_connect = new db.connect_mysql();
        product my_product = null;
        try {
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
                out.println("<title>Servlet confirmation</title>");
                out.println("</head>");
                out.println("<body>\n"
                        + "<img src=\"img/home/img1_.jpg\" id=\"sfondo1\" />\n"
                        + "<img src=\"img/home/img2_.jpg\" id=\"sfondo2\" />");
                out.println("<header><img src=\"img/cart.png\" class=\"account\" /><img src=\"img/user.png\" class=\"account\" /><img src=\"img/settings.png\" class=\"account\" /><p>Salve " + user + "</p><form method='post' action='logout'><input type='submit' id=\"logout\" value='Log-out' /></form></header>");
                out.println("<div id=\"container_spesa\">");
                out.println("<form action='finish_page' id='confirmation_form' name='confirmation_form' method='post' >");
                out.println("<table id='products' CELLSPACING='0' BORDER='0' >");
                out.println("<tr><th>Prodotto</th><th>Quantità</th><th>Prezzo</th></tr>");
                DecimalFormat df = new DecimalFormat("#.##");
                double totale = 0.0;
                out.println("<input type='hidden' value='' id='submit' name='submit' />");
                String id_product = "";
                session.setAttribute("carrello", carrello);
                for (int i = 0; i < carrello.length; i++) {
                    my_product = my_connect.get_product(carrello[i]);
                    out.println("<tr><td>" + my_product.name + "</td>");
                    out.println("<td> 1 " + my_product.UM + "</td>");
                    out.println("<td>" + my_product.price + "</td></tr>");
                    totale += Double.valueOf(my_product.price);
                    id_product = my_product.category;
                }
                out.println("<input type=\"hidden\" value=\"" + id_product + "\" id=\"id_product\" name=\"id_product\" />");
                out.println("<tr id='totale'><td colspan='3' >Totale Spesa " + df.format(totale) + "</td></tr>");
                out.println("<tr><td colspan='3' style='text-align:right'><button onclick='submit_form(this.id);' id='annulla' name='annulla'>Annulla</button><button onclick='submit_form(this.id);' id='conferma' name='conferma' >Conferma</button></td></td></tr>");
                out.println("</table>");
                out.println("</form>");
                out.println("</div>");
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
            Logger.getLogger(confirmation_page.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(confirmation_page.class.getName()).log(Level.SEVERE, null, ex);
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
