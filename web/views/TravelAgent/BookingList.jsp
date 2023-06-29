<%-- 
    Document   : LandingPage
    Created on : May 17, 2023, 8:05:10 PM
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
        <script src="js/jquery-3.7.0.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <title>EasyTravel | Booking list</title>
    </head>
    <body>
        <c:import url="../Layout/Header.jsp"></c:import>
        <div id="accordion" class="w-75 mx-auto mt-4">
            <div class="row card-header font-weight-bold">
                <div class="col-1">
                    #
                </div>
                <div class="col-2">
                    Book time
                </div>
                <div class="col-3">
                    Tourist name
                </div>
                <div class="col-2">
                    Start date
                </div>
                <div class="col-2">
                    Tourists quantity
                </div>
                <div class="col-2">
                    Status
                </div>
            </div>
            <c:forEach items="${list}" var="booking" varStatus="loop">
                <c:set var="idx" value="${loop.count+page.itemsPerPage*(page.index-1)}"></c:set>
                <div class="card">
                    <div class="card-header row" id="heading${idx}" role="button" 
                        onclick="this.children[0].click()">
                        <button class="btn collapsed" data-toggle="collapse" data-target="#collapse${idx}" 
                                aria-expanded="false" aria-controls="collapse${idx}" type="button" hidden="">
                        </button>
                        <div class="col-1">
                            ${idx}
                        </div>
                        <div class="col-2">
                            ${booking.bookTime}
                        </div>
                        <div class="col-3">
                            ${booking.touristName}
                        </div>
                        <div class="col-2">
                            ${booking.startDate}
                        </div>
                        <div class="col-2">
                            ${booking.touristsQuantity}
                        </div>
                        <div class="col-2 ${booking.status=='Processing'?'text-warning':
                                     booking.status=='Ready'?'text-primary':'text-success'}">
                            ${booking.status}
                        </div>
                    </div>
                    <div id="collapse${idx}" class="collapse" data-parent="#accordion" aria-labelledby="heading${idx}">
                        <div class="card-body row">
                            <div class="col-9">
                                <b>Tourist phone: </b>${booking.touristPhone}
                                <br>
                                <b>Tourist email: </b>${booking.touristEmail}
                                <br>
                                <b>Note: </b>${booking.note}
                            </div>
                            <div class="col-1">
                                <a href="#" class="btn btn-primary">Process</a>
                            </div>
                            <div class="col-2">
                                <a href="tour?id=${booking.tourId}" class="btn btn-info">Go to tour</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div> 
    </body>
</html>
