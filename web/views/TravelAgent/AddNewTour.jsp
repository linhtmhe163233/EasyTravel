<%-- 
    Document   : LandingPage
    Created on : 17/05/2023
    Author     : DucTM
    Update on  : 29/05/2023, implements some elements
    Update on  : 31/05/2023, add date constraints
    Update on  : 16/06/2023, adjust page for edit tour
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:import url="../Layout/Header.jsp"></c:import>
        <c:if test="${requestScope.tour==null}">
            <h1 class="text-center mb-4">Add a new tour</h1>
        </c:if>
        <c:if test="${requestScope.tour!=null}">
            <h1 class="text-center mb-4">Edit tour info</h1>
        </c:if>
        <form method="POST" action="/EasyTravel/tours" class="w-75 mx-auto needs-validation" 
              enctype="multipart/form-data" novalidate>
            <input type="hidden" name="id" id="id" value="${requestScope.tour.id}">
            <div class="form-row">
                <div class="form-group col-6">
                    <label data-error="wrong" data-success="right" for="name">Tour name</label>
                    <input type="text" class="form-control validate" id="name" placeholder="Tour name" 
                           name="name" required maxlength="100" pattern="^\s*[\p{L}0-9]+(\s[\p{L}0-9]+)*\s*$"
                           value="${requestScope.tour.name}">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Name must be fewer than 100 letters and spaces and not empty!</div>
                </div>
                <div class="form-group col-6">
                    <label data-error="wrong" data-success="right" for="destination">Destination</label>
                    <input type="text" class="form-control validate" id="destination" placeholder="Destination" 
                           name="destination" required maxlength="50" pattern="^\s*[\p{L}0-9]+(\s[\p{L}0-9]+)*\s*$"
                           value="${requestScope.tour.destination}">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Destination must be fewer than 50 letters and spaces and not empty!</div>
                </div>
            </div>
            <div class="form-row border-bottom border-info pb-3">
                <div class="form-group col-6">
                    <label  data-error="wrong" data-success="right" for="type">Type</label>
                    <input type="text" class="form-control validate" id="type" placeholder="Tour type"
                           name="type" required maxlength="50" pattern="^\s*\p{L}+(\s\p{L}+)*\s*$"
                           value="${requestScope.tour.type}">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Type must be fewer than 50 letters and spaces and not empty!</div>
                </div>
                <div class="form-group col-6">
                    <label  data-error="wrong" data-success="right" for="price">Price</label>
                    <input type="number" class="form-control validate" id="price" placeholder="5,000,000" 
                           name="price" step=100000 required min="100000" value="${requestScope.tour.price}">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Enter a valid number!</div>
                </div>
            </div>
            <div class="form-row mt-3">
                <div class="form-group col-6">
                    <label data-error="wrong" data-success="right" for="available_from">Available from</label>
                    <input type="date" class="form-control validate" id="available_from" name="available_from" required
                           value="${requestScope.tour.availableFrom}">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">This date cannot be in the past!</div>
                </div>
                <div class="form-group col-6">
                    <label data-error="wrong" data-success="right" for="available_to">Available to</label>
                    <input type="date" class="form-control validate" id="available_to" name="available_to" required
                           value="${requestScope.tour.availableTo}">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">This date cannot be in the past!</div>
                </div>
            </div>
            <div class="form-row border-bottom border-info pb-3">
                <div class="form-group col-6">
                    <label data-error="wrong" data-success="right" for="trip_length">Trip length (days)</label>
                    <input type="number" class="form-control" id="trip_length" placeholder="3" 
                           name="trip_length" required min="1" value="${requestScope.tour.tripLength}">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Enter a valid number!</div>
                </div>
                <div class="form-group col-6">
                    <label data-error="wrong" data-success="right" for="max_quantity">Max number of passengers</label>
                    <input type="number" class="form-control validate" id="max_quantity" placeholder="5" 
                           name="max_quantity" required min="1" value="${requestScope.tour.maxQuantity}">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Enter a valid number!</div>
                </div>
            </div>
            <p class="mt-3 mb-0">Image</p>
            <div class="custom-file mb-3">
                <input type="file" class="custom-file-input validate" id="image" name="image" required  
                       accept="image/*">
                <label data-error="wrong" data-success="right" class="custom-file-label" for="image">Choose image</label>
                <div class="valid-feedback">Looks good!</div>
                <div class="invalid-feedback">Update image!</div>
            </div>
            <c:if test="${requestScope.tour!=null}">
                <img id="imageDisplay" class="img-fluid" src="images/${requestScope.tour.image}">
            </c:if>
            <c:if test="${requestScope.tour==null}">
                <img id="imageDisplay" class="img-fluid">
            </c:if>
            <div class="form-group mt-3 ">
                <label data-error="wrong" data-success="right" for="description">Description</label>
                <textarea class="form-control validate" id="description" rows="3" 
                          name="description" required maxlength="300">${requestScope.tour.description}</textarea>
                <div class="valid-feedback">Looks good!</div>
                <div class="invalid-feedback">Write something about your tour</div>
            </div>  
            <button type="submit" class="btn btn-primary">Save</button>
            <c:if test="${requestScope.tour==null}">
                <a href="home" class="btn btn-danger">Cancel</a>
            </c:if>
            <c:if test="${requestScope.tour!=null}">
                <a href="tour?id=${requestScope.tour.id}" class="btn btn-danger">Cancel</a>
            </c:if>
        </form>
    </body>
    <script src="js/AddNewTour.js"></script>
    <script src="js/bootstrap.min.js"></script>
</html>
