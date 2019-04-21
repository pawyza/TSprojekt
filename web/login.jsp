<%-- 
    Document   : login
    Created on : 2019-04-21, 16:26:36
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="login" method="POST">
            Login: <input type="text" name="login" /> <br />
            Password: <input type="text" name="password" /> <br />
            <input type="submit" value="Log in" />
        </form>
    </body>
</html>
