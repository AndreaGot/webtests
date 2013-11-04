/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sito;

import db.connect_mysql;
import db.product;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.text.DecimalFormat;
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
 * @author Mattia
 */
public class product_page extends HttpServlet {

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
    public static String getBaseUrl(HttpServletRequest request) {
        if ((request.getServerPort() == 80)
                || (request.getServerPort() == 443)) {
            return request.getScheme() + "://"
                    + request.getServerName()
                    + request.getContextPath();
        } else {
            return request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
        }
    }

    public static boolean exists(String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con =
                    (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        logs mylog = null;
        mylog = new logs();
        ArrayList productsList = null;
        ArrayList productValues = null;
        db.connect_mysql my_connect;
        my_connect = new db.connect_mysql();

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
                out.println("<title>Servlet product</title>");
                out.println("</head>");
                out.println("<body>\n"
                        + "<img src=\"img/home/img1_.jpg\" id=\"sfondo1\" />\n"
                        + "<img src=\"img/home/img2_.jpg\" id=\"sfondo2\" />");
                out.println("<header><img src=\"img/cart.png\" class=\"account\" /><img src=\"img/user.png\" class=\"account\" /><img src=\"img/settings.png\" class=\"account\" /><p>Salve " + user + "</p><form method='post' action='logout'><input type='submit' id=\"logout\" value='Log-out' /></form></header>");
                out.println("<div id=\"container_log\">");
                out.println("<form action='confirmation_page' id='carrello_form' name='carrello_form' method='post' >");
                out.println("<table id='products' CELLSPACING='0' BORDER='0' >");
                out.println("<tr><th></th><th>Prodotto</th><th>Inserizionista</th><th>Quantità</th><th>Prezzo*</th><th>Prezzo Totale</th><th>Fotografia</th></tr>");
                productsList = my_connect.get_products(request.getParameter("id_product"));
                Iterator itr_list_products = productsList.iterator();
                int i = 0;
                DecimalFormat df = new DecimalFormat("#.##");
                while (itr_list_products.hasNext()) {
                    i = i % 2;
                    String rowClass = "";
                    if (i == 0) {
                        rowClass = "row1";
                    } else {
                        rowClass = "row2";
                    }
                    i++;
                    product prodotto = (product) itr_list_products.next();
                    if (prodotto.quantity != "0") {
                        out.println("<tr class='" + rowClass + "' onclick=''>");
                        out.println("<td><input type='checkbox' value='" + prodotto.id + "' name='carrello' id='" + prodotto.id + "' /></td>");
                        out.println("<td>" + prodotto.name + "</td>");
                        out.println("<td>" + prodotto.id_user + "</td>");
                        out.println("<td>" + prodotto.quantity + " " + prodotto.UM + "</td>");
                        out.println("<td>" + prodotto.price + "</td>");
                        out.println("<td>" + df.format(Double.parseDouble(prodotto.price) * Integer.parseInt(prodotto.quantity)) + "</td>");
                        String getURL = getBaseUrl(request);
                        if (exists(getURL + "/" + prodotto.photo)) {
                            out.println("<td><img HEIGHT='80' src='" + prodotto.photo + "' /></td>");
                        } else {
                            out.println("<td><img src='img/products/vuota.png' /></td>");
                        }
                        out.println("</tr>");
                    } else {
                        i--;
                    }
                }
                out.println("<tr><td colspan='7' style='text-align:right'><input type='button' onClick='history.back()' id='annulla' name='annulla' value='Indietro'/><input type='submit' value='Compra' id='compra' name='compra' /></td></td></tr>");
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
        } catch (Exception e) {
            mylog.write(e.getMessage());
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
            Logger.getLogger(product_page.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(product_page.class.getName()).log(Level.SEVERE, null, ex);
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
