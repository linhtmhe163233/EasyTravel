+<%-- 
    Document   : LandingPage
    Created on : 17/05/2023
    Author     : DucTM
    Update on  : 29/05/2023, implements some elements
    Update on  : 31/05/2023, add date constraints
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="EasyTravel">
        <meta name="keywords" content="travel">
        <meta name="author" content="Group6">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <title>Easy Travel | Add tour</title>
    </head>
    <body>
        <h1 class="text-center mb-4">Add a new tour</h1>
        <form method="POST" action="/EasyTravel/tours" class="w-75 mx-auto" enctype="multipart/form-data">
            <div class="form-row">
                <div class="form-group col-6">
                    <label for="name">Tour name</label>
                    <input type="text" class="form-control" id="name" placeholder="Tour name" 
                           name="name" required maxlength="100">
                </div>
                <div class="form-group col-6">
                    <label for="destination">Destination</label>
                    <input type="text" class="form-control" id="destination" placeholder="Destination" 
                           name="destination" required maxlength="50">
                </div>
            </div>
            <div class="form-row border-bottom border-info pb-3">
                <div class="form-group col-6">
                    <label for="type">Type</label>
                    <input type="text" class="form-control" id="type" placeholder="Tour type"
                           name="type" required maxlength="50">
                </div>
                <div class="form-group col-6">
                    <label for="price">Price</label>
                    <input type="number" class="form-control" id="price" placeholder="5,000,000" 
                           name="price" step=100000 required min="100000">
                </div>
            </div>
            <div class="form-row mt-3">
                <div class="form-group col-6">
                    <label for="available_from">Available from</label>
                    <input type="date" class="form-control" id="available_from" name="available_from" required>
                </div>
                <div class="form-group col-6">
                    <label for="available_to">Available to</label>
                    <input type="date" class="form-control" id="available_to" name="available_to" required>
                </div>
            </div>
            <div class="form-row border-bottom border-info pb-3">
                <div class="form-group col-6">
                    <label for="trip_length">Trip length (days)</label>
                    <input type="number" class="form-control" id="trip_length" placeholder="3" 
                           name="trip_length" required min="1">
                </div>
                <div class="form-group col-6">
                    <label for="max_quantity">Max number of passengers</label>
                    <input type="number" class="form-control" id="max_quantity" placeholder="5" 
                           name="max_quantity" required min="1">
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
        </form>
    </body>
    <script src="./js/AddNewTour.js"></script>
</html>
