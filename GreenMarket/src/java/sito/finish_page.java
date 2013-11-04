/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sito;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import db.product;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
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
public class finish_page extends HttpServlet {

    private Document document;

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
            throws DocumentException, ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        db.connect_mysql my_connect;
        my_connect = new db.connect_mysql();
        logs mylog = null;
        mylog = new logs();
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
                        + "<script type=\"text/javascript\" async=\"\" src=\"js/greenmarket.js\"></script>"
                        + "<script type=\"text/javascript\" async=\"\" src=\"js/finish_page.js\"></script>");
                out.println("</head>");
                out.println("<body>");
                String product = request.getParameter("id_product");
                String submit = request.getParameter("submit");
                if (submit.equals("annulla")) {
                    request.getSession().removeAttribute("carrello");
                    out.println("<form action='product_page' name='sub_final' id='sub_final' method='POST' >");
                    out.println("<input type=\"hidden\" value=\"" + product + "\" id=\"id_product\" name=\"id_product\" />");
                    out.println("<input type='submit' value='test' />");
                    out.println("</form>");
                } else {
                    try {

                        String fileName = "/" + new Date().getTime();
                        String path = "pdf/" + user;
                        String path_ = request.getRealPath("");
                        int index = path_.indexOf("build");
                        String real_path = path_.substring(0, index);
                        real_path = real_path.replace("\\", "/");
                        boolean test = new File(real_path + path).mkdirs();
                        String path_pdf = real_path + path + fileName + ".pdf";
                        OutputStream file = new FileOutputStream(new File(path_pdf));
                        document = new Document();
                        PdfWriter.getInstance(document, file);
                        double totale = 0.0;
                        document.open();
                        String[] carrello = (String[]) session.getAttribute("carrello");
                        document.add(new Paragraph("Ricevuta cliente : " + user));
                        document.add(new Paragraph("In data: " + new Date().toString()));
                        for (int i = 0; i < carrello.length; i++) {
                            my_product = my_connect.get_product(carrello[i]);
                            document.add(new Paragraph("1 " + my_product.UM + " " + my_product.name + " prezzo: " + my_product.price + "€"));
                            String Query = "UPDATE products SET quantity = quantity -1 WHERE id =" + carrello[i];
                            String Query_ = "INSERT INTO sell (date,id_product,id_user,quantity,price,receipt) VALUES (NOW()," + my_product.id + "," + my_connect.get_id_from_user(user) + ",1," + my_product.price + ",'" + path_pdf + "')";
                            my_connect.Insert(Query);
                            my_connect.Insert(Query_);
                            totale += Double.valueOf(my_product.price);
                        }
                        document.add(new Paragraph("Totale: " + totale + "€"));
                        document.close();
                        file.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    document.close();
                }

                out.println("");
                out.println("</body>");
                out.println("</html>");
                response.sendRedirect("buyer");
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
            Logger.getLogger(finish_page.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(finish_page.class.getName()).log(Level.SEVERE, null, ex);
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
