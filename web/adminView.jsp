<%-- 
    Document   : adminView
    Created on : 2019-04-22, 19:30:56
    Author     : Jakub Siembida
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
        <c:out value="${'Zalogowano jako administrator, witaj '}${client.getForename()}" />
        
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
        <div></div>
        <form action="carchange" method="POST">
            <input type="radio" name="mode" value="add"> dodaj samochód<br>
            <input type="radio" name="mode" value="edit"> edytuj samochód<br>
            <input type="radio" name="mode" value="delete"> usuń samochód<br>
            Id <input type="text" name="id" /><br>
            Numer rejestracyjny <input type="text" name="regnmbr" /><br>
            Marka <input type="text" name="brand" /><br>
            Model <input type="text" name="model" /><br>
            Koszt jednodniowy <input type="text" name="cost" /><br>
            <button>wykonaj</button>
        </form>
        
        <br>
        <h2>Lista wszystkich rezerwacji:</h2>
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
        <div>usuń rezerwację</div>
        <form action="reschange" method="POST">
            Id <input type="text" name="id" /><br>
            <button>wykonaj</button>
        </form>
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>    
</html>
