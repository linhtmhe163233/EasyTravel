<%-- 
    Document   : LandingPage
    Created on : May 31, 2023
    Author     : DucTM
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
        <link href="css/fontawesome.min.css" rel="stylesheet"/>
        <title>EasyTravel | Staff</title>
    </head>
    <body>
        <h1>Staff</h1>
        <table class="table table-hover table-bordered w-75 mx-auto">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Phone</th>
                    <th scope="col">DOB</th>
                    <th scope="col">Gender</th>
                    <th scope="col" colspan="2"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="staff">
                    <tr>
                        <th scope="row">${staff.ID}</th>
                        <td>${staff.name}</td>
                        <td>${staff.phone}</td>
                        <td>${staff.DOB}</td>
                        <td>${staff.gender}</td>
                        <td><a href="#" class="badge badge-info">Edit</a></td>
                        <td><a href="#" class="badge badge-danger">Delete</a></td>
                    </tr> 
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
