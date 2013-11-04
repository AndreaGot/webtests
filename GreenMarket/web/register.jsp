<%-- 
    Document   : register
    Created on : Jan 22, 2013, 9:51:25 AM
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
                String error_name = null;
                if (request.getParameterMap().containsKey("error")) {
                    error = request.getParameter("error").toString();
                }
                if (request.getParameterMap().containsKey("error_name")) {
                    error_name = request.getParameter("error_name").toString();
                }
            %>

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


        <div id="register">

            <form action="submit_register" onsubmit="return validate();">
                <p>  <span id="error"><% if (error_name != null) {
                                                       out.println("Username già Presente!");
                                                   }%></span>
                <h2>Registrazione</h2>
              
                </p>
                <p>
                    <label id="user_lbl" for="user">Username</label>
                    <input id="user" class="reg" name="user" onfocusout="focusout_login(this.id);" onchange="focus_login(this.id);" onfocus="focus_login(this.id);" />
                </p>
                <p>
                    <label id="psw_lbl" for="psw">Password</label>
                    <input id="psw" class="reg" name="psw" onfocusout="focusout_login(this.id);" onchange="focus_login(this.id);" onfocus="focus_login(this.id);" />
                </p>
                <p>
                    <label id="email_lbl" for="email">Email</label>
                    <input id="email" class="reg" name="email" onfocusout="focusout_login(this.id);" onchange="focus_login(this.id);" onfocus="focus_login(this.id);" />
                </p>
                <p>
                    <label id="email_conf_lbl" for="email_conf">Conferma Email</label>
                    <input id="email_conf" class="reg" name="email_conf" onfocusout="focusout_login(this.id);" onchange="focus_login(this.id);" onfocus="focus_login(this.id);" />
                </p>

                <p>
                    <label id="address_lbl" for="address">Indirizzo</label>
                    <input id="address" class="reg" name="address" onfocusout="focusout_login(this.id);" onchange="focus_login(this.id);" onfocus="focus_login(this.id);" />
                </p>

                <p>
                    <input type="submit" value="REGISTRAMI!" />
                </p>


            </form>
        </div>

        <footer>
            <p>&copy; Fravezzi Mattia 135759 - Tait Luca 145890 - Universita degli studi di Trento </p>
        </footer>
    </body>

</html>

