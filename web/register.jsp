<%-- 
    Document   : register
    Created on : 2019-04-21, 16:30:25
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/bootstrap.min.css">
      <link rel="stylesheet" href="main.css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <section class="loginWindow">
            <div class="container">
                <form action="register" method="POST">
                    Login: <input type="text" name="login" /> <br />
                    Password: <input type="text" name="password" /> <br />
                    Repeat password: <input type="text" name="repassword" /> <br />
                    Forename: <input type="text" name="forename" /> <br />
                    Surname: <input type="text" name="surname" /> <br />
                    Pesel: <input type="text" name="pesel" /> <br />
                    Phone number: <input type="text" name="number" /> <br />
                    <section class = "center">
                        <input type="submit" value="Register" class="loginButton"/>
                    </section>
                </form>
                <section class = "error">
                    ${message}
                </section>
            </div>
        </section>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
