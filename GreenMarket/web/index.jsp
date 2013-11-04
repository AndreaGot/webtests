<%-- 
    Document   : index
    Created on : Nov 10, 2012, 3:01:17 PM
    Author     : Mattia
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GreenMarket</title>
        <script type="text/javascript" async="" src="js/jquery-1.8.2.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/nologged.css">
        <link rel="stylesheet" type="text/css" href="css/greenMarket.css">
        <script type="text/javascript" async="" src="js/greenmarket.js"></script>  
    </head>
    <body>
        <img src="img/home/img1_.jpg" id="sfondo1" />
        <img src="img/home/img2_.jpg" id="sfondo2" />
        <header>
            <a href="./"><img src="img/logoo.png" id="logo"  /></a>

            <%
                String error = null;
                String ok = null;
                if (request.getParameterMap().containsKey("error")) {
                    error = request.getParameter("error").toString();
                }
                if (request.getParameterMap().containsKey("ok")) {
                    ok = request.getParameter("ok").toString();
                }
            %>
            <h3 id="ok"><% if (ok != null) {
                                out.println("Complimenti Utente Registrato Correttamente");
                            }%></h3>
            <div id="all_login" <% if (error != null) {
                    out.println("style=\"margin-right:0px\"");
                }%> >
                <div id="open_login" onclick="div_login();">
                    <input type="hidden" value="0" id="show_login" /><img src="img/login.png"  /></div>
                <div id="login">
                    <form action="home_page" method="post" id="form-reg" name="form-reg">
                        <p>
                            <label id="name_lbl" for="name" >Username</label>
                            <input type="text" name="name" id="name" class="log_input"  onfocusout="focusout_login(this.id);" onchange="focus_login(this.id);" onfocus="focus_login(this.id);" />
                        </p>
                        <p>
                            <label id="password_lbl"for="password" >Password</label>
                            <input type="password" name="password" id="password" class="log_input"  onfocusout="focusout_login(this.id);" onchange="focus_login(this.id);" onfocus="focus_login(this.id);" />
                            <br />
                            <span id="error"><% if (error != null) {
                                out.println("Credenziali non corrette");
                            }%></span>
                        </p>

                        <p>
                            <a href="register.jsp">Registrati</a>
                        </p>
                        <p>
                            <input type="submit" value="Log-in" id="submit" />
                        </p>
                    </form>
                </div>  
            </div>
            <div class="clear"></div>

        </header>

        <div id="container">
            <div id="show">
                <div id="vini" class="offerte"></div>
                <div id="formaggi"  class="offerte"></div>
                <div id="pane"  class="offerte"></div>
                <div id="ortaggi"  class="offerte"></div>
            </div>
            <div class="clear"></div>
        </div>
        <footer>
            <p>&copy; Fravezzi Mattia 135759 - Tait Luca 145890 - Universita degli studi di Trento </p>
        </footer>
    </body>

</html>
