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
            <div class="">
                <h1 class="text-center">Hotels</h1>
                <button class="btn btn-primary btn-rounded mr-4 float-right" 
                        data-toggle="modal" data-target="#modalAddHotel" id="add">
                    Add
                </button>
            </div>
            <table class="table table-hover table-bordered w-75 mx-auto" id="hotelTable">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Stars</th>
                        <th scope="col">Room available</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Location</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="hotel">

                    <tr>
                        <th scope="row">${hotel.id}</th>
                        <td>${hotel.name}</td>
                        <td>${hotel.stars}</td>
                        <td>${hotel.room_available}</td>
                        <td>${hotel.phone}</td>
                        <td>${hotel.location}</td>
                        <td><a href="edit?HotelId=${hotel.id}" class="badge badge-info">Edit</a></td>
                        <td><a href="#"  onclick="showMess(${hotel.id})" class="badge badge-danger">Delete</a></td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="modal fade" id="modalAddHotel" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h4 class="modal-title w-100 font-weight-bold">Add Hotel Info</h4>
                        <button type="button" class="close btn btn-danger" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="/EasyTravel/hotels" method="POST" novalidate id="form" class="needs-validation">
                        <div class="modal-body mx-3">
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="name">Hotel name</label>
                                <input type="text" id="name" class="form-control validate" name="name" required maxlength="80"
                                       pattern="^\s*\p{L}+(\s\p{L}+)*\s*$" value="${hotel.name}">
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Name must be fewer than 80 letters and spaces and not empty!</div>
                            </div>
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="name">Stars</label>
                                <input type="number" id="stars" class="form-control validate" name="stars" required 
                                       min="1" max="5" value="${hotel.stars}">
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Please enter the number of stars from 1 to 5!</div>
                            </div>
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="name">Room available</label>
                                <input type="number" id="room_available" class="form-control validate" name="room_available" required
                                       min="10" max="100" value="${hotel.room_available}">
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Please enter a valid room number (10-100)!</div>
                            </div>
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="phone">Phone number</label>
                                <input type="text" id="phone" class="form-control validate" name="phone" required 
                                       pattern="^0[0-9]{9}$" value="${hotel.phone}">
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Phone number can only contain 10 digits! Ex:0123456789</div>
                                <c:if test="${message!=null}">
                                    <div class="text-danger">Phone must be unique!</div>
                                </c:if>
                            </div>
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="name">Location</label>
                                <input type="text" id="location" class="form-control validate" name="location" required maxlength="50"
                                       pattern="^\s*\p{L}+(\s\p{L}+)*\s*$" value="${hotel.location}">
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Location must be fewer than 50 letters !</div>
                            </div>
                            <div class="modal-footer d-flex justify-content-center">
                                <button class="btn btn-primary" type="submit">Confirm</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <c:import url="../Layout/Footer.jsp"></c:import>
        </body>
        <script>
            function showMess(id) {
                var option = confirm('are you sure to delete');
                if (option === true) {
                    window.location.href = 'delete?HotelId' + id;
                }
            }
        </script>
        <script src="js/jquery-3.7.0.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/HotelList.js"></script>
        <script>
            let message = '${requestScope.message}';
            if (message !== '') {
                document.getElementById("add").click();
            }
    </script>
</html>
