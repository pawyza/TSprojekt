<%-- 
    Document   : result
    Created on : 2019-04-16, 18:07:42
    Author     : User
--%>

<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clients</title>
    </head>
    <body>
        <h2>Lista klientów:</h2>
        <table style="width:50%" border="1">
            <tr>
                <th>Imię</th>
            </tr>
           <c:forEach var="client" items ="${clients}">
               <tr>
                   <td><c:out value="${client.getForename()}"/></td>
               </tr>
           </c:forEach>
        </table>
    </body>
</html>
