<%-- 
    Document   : index
    Created on : 4-nov-2013, 22.25.35
    Author     : ANDre1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        In questa pagina potrai settare, vedere o cancellare cookies
        <form action="SetCookies" method="GET">
            <input type="submit" value="Set Cookies">
        </form>
        <form action="GetCookies" method="GET">
            <input type="submit" value="Get Cookies">
        </form>
         <form action="DeleteCookies" method="GET">
            <input type="submit" value="Delete Cookies">
        </form>
    </body>
</html>
