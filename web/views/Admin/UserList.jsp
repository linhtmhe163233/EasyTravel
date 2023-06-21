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

        <meta name="description" content="EasyTravel">
        <meta name="keywords" content="travel">
        <meta name="author" content="Group6">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link href="css/fontawesome.min.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EasyTravel | Staff</title>


    </head>
    <body>
        <c:import url="../Layout/Header.jsp"></c:import>
            <table class="table table-hover table-bordered w-75 mx-auto" id="staffTable">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Fullname</th>
                        <th scope="col">Username</th>
                        <th scope="col">Password</th>
                        <th scope="col">DOB</th>
                        <th scope="col">Email</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Role</th>
                        <th scope="col">Status</th>
<!--                        <th scope="col" colspan="2"></th>-->
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="user">

                    <tr>
                        <th scope="row">${user.id}</th>
                        <td>${user.fullname}</td>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>${user.dob}</td>
                        <td>${user.email}</td>
                        <td>${user.phone}</td>
                        <td>${user.role}</td>
                        
                        <td><a href="edit?HotelId=${hotel.id}" class="badge badge-info">Active</a></td>
<!--                        <td><a href="#"  onclick="showMess(${hotel.id})" class="badge badge-danger">Delete</a></td>-->

                    </tr>
                </c:forEach>
            </tbody>
    </body>
    <script src="js/jquery-3.7.0.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/StaffList.js"></script>
   
</html>
