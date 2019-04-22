<%-- 
    Document   : register
    Created on : 2019-04-21, 16:30:25
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <form action="register" method="POST">
            Login: <input type="text" name="login" /> <br />
            Password: <input type="text" name="password" /> <br />
            Repeat password: <input type="text" name="repassword" /> <br />
            Forename: <input type="text" name="forename" /> <br />
            Surname: <input type="text" name="surname" /> <br />
            Pesel: <input type="text" name="pesel" /> <br />
            Phone number: <input type="text" name="number" /> <br />
            <input type="submit" value="Register" />
        </form>
        ${message}
    </body>
</html>
