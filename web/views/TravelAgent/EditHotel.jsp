<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="EasyTravel">
        <meta name="keywords" content="travel">
        <meta name="author" content="Group6">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <title>Easy Travel | Edit Hotel</title>
    </head>
    <body>
        <c:import url="../Layout/Header.jsp"></c:import>
        <div class="container">
            <h1 class="text-center">Edit Hotel</h1>
            <form action="/EasyTravel/edit" method="POST" novalidate id="form" class="needs-validation">
                 <div class="form-group">
                    <label for="name">Hotel Id</label>
                    <input value="${hotel.id}" type="text" id="id" class="form-control" name="id" readonly>                          
                </div>
                <div class="form-group">
                    <label for="name">Hotel name</label>
                    <input value="${hotel.name}" type="text" id="name" class="form-control" name="name" required maxlength="80"
                           pattern="^\s*\p{L}+(\s\p{L}+)*\s*$">
                    <div class="invalid-feedback">Name must be fewer than 80 letters and not empty!</div>
                </div>
                <div class="form-group">
                    <label for="stars">Stars</label>
                    <input type="number" id="stars" class="form-control" name="stars" required 
                           min="1" max="5" value="${hotel.stars}">
                    <div class="invalid-feedback">Please enter stars from 1 to 5!</div>
                </div>
                <div class="form-group">
                    <label for="room_available">Room available</label>
                    <input type="number" id="room_available" class="form-control" name="room_available" required
                           min="10" max="100" value="${hotel.room_available}">
                    <div class="invalid-feedback">Please enter a valid room number (10-100)!</div>
                </div>
                <div class="form-group">
                    <label for="phone">Phone number</label>
                    <input type="text" id="phone" class="form-control" name="phone" required 
                           pattern="^0[0-9]{9}$" value="${hotel.phone}">
                    <div class="invalid-feedback">Phone number can only contain 10 digits! Ex: 0123456789</div>
                </div>
                <div class="form-group">
                    <label for="location">Location</label>
                    <input type="text" id="location" class="form-control" name="location" required maxlength="50"
                           pattern="^\s*\p{L}+(\s\p{L}+)*\s*$" value="${hotel.location}">
                    <div class="invalid-feedback">Location must be fewer than 50 letters!</div>
                </div>
                 <div class="modal-footer d-flex justify-content-center">
                            <button class="btn btn-primary" type="submit">Confirm</button>
                        </div>
        </div>
<script src="js/jquery-3.7.0.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/HotelList.js"></script>
    </body>
</html>