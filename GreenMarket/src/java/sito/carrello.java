/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sito;

import db.product;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Luca
 */
public class carrello extends HttpServlet {

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
        logs mylog = null;
        mylog = new logs();
        ArrayList pdfList = null;
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
            if (type_user.equals("buyer")) {
                out.println("<html>");
                out.println("<head>"
                        + "<script type=\"text/javascript\" async=\"\" src=\"js/jquery-1.8.2.min.js\"></script>\n"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/logged.css\">\n"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/greenMarket.css\">"
                        + "<script type=\"text/javascript\" async=\"\" src=\"js/greenmarket.js\"></script>");
                out.println("<title>Servlet cart</title>");
                out.println("</head>");
                out.println("<body>\n"
                        + "<img src=\"img/home/img1_.jpg\" id=\"sfondo1\" />\n"
                        + "<img src=\"img/home/img2_.jpg\" id=\"sfondo2\" />");
                out.println("<header><img  src=\"img/cart.png\" class=\"account\" /><img src=\"img/user.png\" class=\"account\" /><img src=\"img/settings.png\" class=\"account\" /><p>Salve " + user + "</p><form method='post' action='logout'><input type='submit' id=\"logout\" value='Log-out' /></form></header>");
                out.println("<div id=\"container_log\">");
                out.println("<table id='products' CELLSPACING='0' BORDER='0' >");
                out.println("<tr><th>Data</th><th>Link PDF</th></tr>");
                String id_user = my_connect.get_id_from_user(user);
                pdfList = my_connect.get_list_pdf(id_user);
                Iterator itr_list_products = pdfList.iterator();
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
                    String[] str = (String[]) itr_list_products.next();
                    out.println("<td class='row1'>" + str[0] + "</td>");
                    out.println("<td class='row2'><a target='_blank' href='download?path=" + str[1] + "'> <img src='img/pdf.png' /> Download here</a></td>");
                    out.println("</tr>");
                }
                out.println("<tr><td colspan='7' style='text-align:right'><input type='button' onClick='history.back()' id='annulla' name='annulla' value='Indietro'/></td></td></tr>");
                out.println("</table>");
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
            Logger.getLogger(carrello.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(carrello.class.getName()).log(Level.SEVERE, null, ex);
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
