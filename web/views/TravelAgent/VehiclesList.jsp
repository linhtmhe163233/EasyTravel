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
        <title>Easy Travel | Vehicles</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <c:import url="../Layout/Header.jsp"></c:import>
        <div class="text-center mt-5">
            <h1>View a list vehicle</h1>
        </div>
        <div class="mr-4 mb-4 d-flex flex-row justify-content-end">
            <button class="btn btn-primary" data-toggle="modal" 
                    data-target="#modalAddVehicle" id="add">Add</button>
        </div>


        <table class="table table-bordered table-striped w-75 mx-auto">
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
                <c:forEach var="vehicle" items="${list}">
                    <tr>
                        <th scope="row">${vehicle.id}</th>
                        <td>${vehicle.type}</td>
                        <td>${vehicle.driverName}</td>
                        <td>${vehicle.driverPhone}</td>
                        <td>${vehicle.maxPassenger}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="modal fade" id="modalAddVehicle" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h4 class="modal-title w-100 font-weight-bold">Add a new vehicle</h4>
                        <button type="button" class="close btn btn-danger" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="/EasyTravel/vehicles" method="POST" novalidate id="form" class="needs-validation">
                        <div class="modal-body mx-3">
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="type">Type</label>
                                <input type="text" id="type" class="form-control validate" name="type" 
                                       required maxlength="30" value="${vehicle.type}">
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Type must be fewer than 30 characters and not empty!</div>
                            </div>
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="driverName">Driver's name</label>
                                <input type="text" id="driverName" class="form-control validate" name="driverName" 
                                       required maxlength="80" pattern="^\s*\p{L}+(\s\p{L}+)*\s*$" value="${vehicle.driverName}">
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Name must be fewer than 80 letters and spaces and not empty!</div>
                            </div>
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="driverPhone">Phone number</label>
                                <input type="text" id="driverPhone" class="form-control validate" name="driverPhone" required 
                                       pattern="^0[0-9]{9}$" value="${vehicle.driverPhone}">
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Phone number can only contain 10 digits! Ex:0123456789</div>
                                <div class="text-danger">${message}</div>
                            </div>
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="maxPassenger">Max passengers number</label>
                                <input type="number" id="maxPassenger" class="form-control" name="maxPassenger" 
                                       required value="${vehicle.maxPassenger}" max="80", min="4">
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Enter a valid number</div>
                            </div>
                        </div>
                        <div class="modal-footer d-flex justify-content-center">
                            <button class="btn btn-primary" type="submit">Confirm</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script src="js/jquery-3.7.0.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/VehicleList.js" type="text/javascript"></script>
    <script>
        let message = '${requestScope.message}';
        if (message !== '') {
            document.getElementById("add").click();
        }
    </script>
</html>
