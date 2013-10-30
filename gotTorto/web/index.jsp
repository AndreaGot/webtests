<%-- 
    Document   : index
    Created on : 29-ott-2013, 12.33.36
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
        <h1>Hello World!</h1>
        
        <form name="form1" action="LoginServlet" method="POST">

            <input type="text" name="username" value="user" />
            <input type="text" name="password" value="pass" />
            <input type="submit" value="ok" name="ok" />

        </form>
    </body>
</html>
