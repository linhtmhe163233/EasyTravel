<%-- 
    Document   : LandingPage
    Created on : May 17, 2023, 8:05:10 PM
    Author     : tranm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
        <title>Easy Travel | Tour detail</title>
    </head>
    <body>
        <c:import url="./Layout/Header.jsp"></c:import>
        <div class="rounded w-75 mx-auto" style="background: #DDD0C8;">
            <h1 class="text-center mt-2" style="color: #323232;">${tour.name}</h1>
            <img class="img-fluid img-thumbnail d-block mx-auto" 
                 src="./images/${tour.image}" alt="${tour.name}"
                 style="height: 50vh;">
            <div class="float-right mr-4">
                <c:if test="${sessionScope.user!=null && sessionScope.user.role=='Travel Agent'}">
                    <button type="button" class="btn btn-info">
                        Edit
                    </button>
                    <button type="button" class="btn btn-danger">
                        Delete
                    </button>
                </c:if>
                <c:if test="${sessionScope.user!=null && sessionScope.user.role=='Tourist'}">
                    <button type="button" class="btn btn-primary">
                        Book
                    </button>
                </c:if>

            </div>
            <div class="pb-4"></div>
        </div>
        <div class="rounded w-75 mx-auto" style="background: #DDD0C8; color: #323232;">
            <h2 class="ml-4 mt-2">Detail</h2>   
            <div class="ml-4 mt-2"><span class="font-weight-bold">Type:</span> ${tour.type}</div>
            <div class="ml-4 mt-2">
                <span class="font-weight-bold">Price: </span><fmt:formatNumber type="number" maxFractionDigits = "3" 
                                  value="${tour.price}"></fmt:formatNumber> VND / ${tour.tripLength} days
            </div>
            <div class="ml-4 mt-2"><span class="font-weight-bold">Available: </span>
                from ${tour.availableFrom} to ${tour.availableTo}
            </div>
            <div class="ml-4 mt-2"><span class="font-weight-bold">Max: </span>
                    ${tour.maxQuantity} people
            </div>
            <div class="ml-4 mt-2">Vehicle, hotel, ... will be assigned after you book</div>
        </div>
        <div class="rounded w-75 mx-auto" style="background: #DDD0C8;color: #323232;">
            <h2 class="ml-4 mt-2">More description</h2>   
            <div class="ml-4 mt-2">${tour.description}</div>
        </div>
        <div class="text-center text-uppercase mt-4">
            Comment section (add later)
        </div>
    </body>
</html>
