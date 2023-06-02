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
        <title>EasyTravel | Vehicles</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <style>
            .title{
                text-align: center;
                background: white;
                width: 20%;
                display: flex;
                justify-content: center;
                border-radius: 20px;
                margin-left: 40%;
            }
            body{
                background: #A9A9A9;
            }
            input{
                height: 40px;
                padding-bottom: 8px;
            }
            .search{
                float: right;
                margin-right: 30px;
            }
            thead{
                background: #3DE397;
            }
            button{
                background: #3DE397 !important;
            }
            .table{
                width: 95%;
                margin-left: 65px;
            }
        </style>
    </head>
    <body>
        <div class="title mt-5">
            <h1>View a list vehicle</h1>
        </div>
        <div class="search">
            <button class="btn"><a href="create-vehicle">Create</a></button>
            <input type="text" placeholder="Search"/>
        </div>

        <div class="mt-5">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Type</th>
                        <th scope="col">Driver Name</th>
                        <th scope="col">Phone Number</th>
                        <th scope="col">Max Passenger</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="a" items="${list}">
                        <tr>
                            <th scope="row">${a.getId()}</th>
                            <td>${a.getType()}</td>
                            <td>${a.getDriver_name()}</td>
                            <td>${a.getDriver_phone()}</td>
                            <td>${a.getMax_passenger()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </body>
</html>
