<%-- 
    Document   : LandingPage
    Created on : May 17, 2023, 8:05:10 PM
    Author     : tranm
--%>

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
        <title>Easy Travel | Hotels</title>
    </head>
    <body>
        <c:import url="../Layout/Header.jsp"></c:import>
        <h1 class="text-center mb-4">Add hotel info</h1>
        <form method="POST" action="/EasyTravel/hotel" class="w-75 mx-auto" enctype="multipart/form-data">
            <div class="form-row">
                <div class="form-group col-6">
                    <label for="name">Hotel name</label>
                    <input type="text" class="form-control" id="name" placeholder="Hotel name" 
                           name="name" required maxlength="100">
                </div>
                <div class="form-group col-6">
                    <label for="id">Id</label>
                    <input type="text" class="form-control" id="id" placeholder="id" 
                           name="id">
                </div>
                <div class="form-group col-6">
                    <label for="address">Address</label>
                    <input type="text" class="form-control" id="address" placeholder="address" 
                           name="address"  required maxlength="100">
                </div>
            <div class="form-row border-bottom border-info pb-3">
                <div class="form-group col-6">
                    <label for="phone">Phone</label>
                    <input type="number" class="form-control" id="phone" required
                           name="phone" required maxlength="10">
                </div>
                 <div class="form-row border-bottom border-info pb-3">
                <div class="form-group col-6">
                    <label for="stars">Stars</label>
                    <input type="text" class="form-control" id="stars" placeholder="stars"
                           name="stars" required maxlength="5">
                </div>
                <div class="form-row border-bottom border-info pb-3">
                <div class="form-group col-6">
                    <label for="room_available">Room_available </label>
                    <input type="text" class="form-control" id="room_available" placeholder="room_available"
                           name="room_available" >
                </div>
                <div class="form-group col-6">
                    <label for="email">Email</label>
                    <input type="text" class="form-control" id="email" 
                           name="Email" >
                </div>
            </div>
            <p class="mt-3 mb-0">Image</p>
            <div class="custom-file mb-3">
                <input type="file" class="custom-file-input" id="image" name="image" required  
                       accept="image/*">
                <label class="custom-file-label" for="image">Choose image</label>
            </div>
            <img id="imageDisplay" class="img-fluid">
            <div class="form-group mt-3 ">
                <label for="description">Description</label>
                <textarea class="form-control" id="description" rows="3" 
                          name="description" required maxlength="300">Lovely tour</textarea>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
            <a href="/EasyTravel/home" class="btn btn-danger">Cancel</a>

    </body>
    <script src="js/AddNewTour.js" type="text/javascript"></script>
</html>
