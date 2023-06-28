<%-- 
    Document   : Header
    Created on : Jun 4
    Author     : DucTM
    Updated on : Jun 15
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!--<!DOCTYPE html>-->
<!--<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="EasyTravel">
        <meta name="keywords" content="travel">
        <meta name="author" content="Group6">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
-->        <script src="js/jquery-3.7.0.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script><!--
</head>
<body>-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="home">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" 
            data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" 
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <c:if test="${sessionScope.user!=null}">
                <c:if test="${sessionScope.user.role=='Travel Agent'}">
                    <a class="nav-item nav-link" href="tours">New tour</a>
                    <a class="nav-item nav-link" href="hotels">Hotels</a>
                    <a class="nav-item nav-link" href="vehicles">Vehicles</a>
                    <a class="nav-item nav-link" href="staff">Staff</a>
                </c:if>  
                <c:if test="${sessionScope.user.role=='Tourist'}">
                    <a class="nav-item nav-link" href="history">Booking history</a>
                </c:if>
                <c:if test="${sessionScope.user.role=='Admin'}">
                    <a class="nav-item nav-link" href="manageuser">Manage</a>
                </c:if>
                <a class="nav-item nav-link" href="profile">Profile</a>
            </c:if>
        </div>
        <div class="navbar-nav ml-auto">
            <c:if test="${sessionScope.user!=null}">
                <a class="nav-item nav-link" href="logout">Logout</a>
            </c:if>
            <c:if test="${sessionScope.user==null}">
                <a class="nav-item nav-link" href="login">Login</a>
            </c:if>
        </div>
    </div>
</nav>
<!--    </body>
</html>-->
