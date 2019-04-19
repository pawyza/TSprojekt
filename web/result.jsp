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
        <title>Client</title>
    </head>
       
        <h2>Uzytkownik <c:out value="${forename}"> <c:out value="${surname}"></h2>
        
        <h2>Lista twoich rezerwacji</h2>

        <c:choose>
            <c:when test="!resId.equals(null)">
            <%-- generowanie tabeli --%>
            <c:otherwise>
            <h2>Brak rezerwacji</h2>
        <h2>Lista samochodów wypożyczalni</h2>
            <%-- generowanie tabeli --%>
    </body>
</html>
