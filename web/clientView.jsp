<%-- 
    Document   : clientView
    Created on : 2019-04-21, 16:53:09
    Author     : User
--%>

<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/bootstrap.min.css">
      <link rel="stylesheet" href="main.css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Konto</title>
    </head>
    <body>
        <section class="center">
            <section style="text-align: right; font-size: 20 px;">
            <c:out value="${'Zalogowany klient '}"/>
            <c:out value="${client.getForename()}"/>
            <c:out value="${' '}"/>
            <c:out value="${client.getSurname()}"/>
            </section>
        
        <h2>Lista samochodów:</h2>
        <table style="width:100%" border="1">
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
        <table style="width:100%" border="1">
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
        <br>
        
        <center>Dodawanie rezerwacji:</center>
            <div class="form">
            <form action="clientView" method="POST">
                Id samochodu: <input type="text" name="id" /><br>
                Wynajęcie od: <input type="text" name="pickupday" /><br>
                Wynajęcie do: <input type="text" name="dropoffday" /><br>
                <section class="center">
                <button class="loginButton">Zarezerwuj</button>
                </section>
            </form>
        </div>
        </section>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>