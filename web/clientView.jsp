<%-- 
    Document   : clientView
    Created on : 2019-04-21, 16:53:09
    Author     : User
--%>

<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Konto</title>
    </head>
    <body>
        <c:out value="${'Zalogowany klient '}"/>
        <c:out value="${client.getForename()}"/>
        <c:out value="${' '}"/>
        <c:out value="${client.getSurname()}"/>
       
        <h2>Lista samochodów:</h2>
        <table style="width:80%" border="1">
            <tr>
                <th>Id</th>
                <th>Numer rejestracyjny</th>
                <th>Marka</th>
                <th>Model</th>
                <th>Koszt jednodniowy</th>
                <th>Termin dostepnosci</th>
            </tr>
           <c:forEach var="car" items ="${cars}">
               <tr>
                   <td><c:out value="${car.getCarId()}"/></td>
                   <td><c:out value="${car.getRegistrationNumber()}"/></td>
                   <td><c:out value="${car.getBrand()}"/></td>
                   <td><c:out value="${car.getModel()}"/></td>
                   <td><c:out value="${car.getPerDayCost()}"/></td>
                   <td><c:out value="${car.getDropOffDay()}" default="${'Dostępny'}"/></td>
               </tr>
           </c:forEach>
        </table>
        <br>
        <h2>Lista twoich rezerwacji:</h2>
        <table style="width:80%" border="1">
            <tr>
                <th>Id rezerwacji</th>
                <th>Id samochodu</th>
                <th>Numer rejestracyjny</th>
                <th>Marka</th>
                <th>Model</th>
                <th>Koszt jednodniowy</th>
                <th>Data wypożyczenia</th>
                <th>Data Zwrotu</th>
                <th>Koszt całkowity</th>
            </tr>
           <c:forEach var="reservation" items ="${reservations}">
               <tr>
                   <td><c:out value="${reservation.getReservationId()}"/></td>
                   <td><c:out value="${reservation.getCarId()}"/></td>
                   <td><c:out value="${reservation.getRegistrationNumber()}"/></td>
                   <td><c:out value="${reservation.getBrand()}"/></td>
                   <td><c:out value="${reservation.getModel()}"/></td>
                   <td><c:out value="${reservation.getPerDayCost()}"/></td>
                   <td><c:out value="${reservation.getPickUpDay()}"/></td>
                   <td><c:out value="${reservation.getDropOffDay()}"/></td>
                   <td><c:out value="${reservation.getTotalCost()}"/></td>
               </tr>
           </c:forEach>
        </table>
    </body>
    </body>
</html>