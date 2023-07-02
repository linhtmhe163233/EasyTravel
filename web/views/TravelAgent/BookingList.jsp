<%-- 
    Document   : LandingPage
    Created on : May 17, 2023, 8:05:10 PM
    Author     : DucTM
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.sql.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
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
        <div id="accordion" class="w-75 mx-auto mt-4" style="min-width: 50rem;">
            <div class="row card-header font-weight-bold">
                <div class="col-1">
                    #
                </div>
                <div class="col-2">
                    Book time
                </div>
                <div class="col-3">
                    Tour name
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
                            ${booking.tourName}
                        </div>
                        <div class="col-2">
                            ${booking.startDate}
                        </div>
                        <div class="col-2">
                            ${booking.touristsQuantity}
                        </div>
                        <div class="col-2 ${booking.status=='Processing'?'text-warning':
                                     booking.status=='Ready'?'text-primary':
                                     booking.status=='Declined'?'text-danger':'text-success'}">
                            ${booking.status}
                        </div>
                    </div>
                    <div id="collapse${idx}" class="collapse" data-parent="#accordion" aria-labelledby="heading${idx}">
                        <div class="card-body row">
                            <div class="col-8">
                                <b>Tourist name </b>${booking.touristName}
                                <br>
                                <b>Tourist phone: </b>${booking.touristPhone}
                                <br>
                                <b>Tourist email: </b>${booking.touristEmail}
                                <br>
                                <b>Tour length: </b>${booking.tourLength}
                                <br>
                                <b>Note: </b>${booking.note}
                                <c:if test="${booking.reason!=null}">
                                    <br>
                                    <b>Reason for cancel:  </b>${booking.reason}
                                </c:if>
                            </div>
                            <div class="col-4">
                                <c:if test="${booking.status!='Done' && booking.status!='Declined'}">
                                    <button class="btn btn-primary" data-toggle="modal" 
                                        data-target="#modalRequest${booking.id}">
                                        Process
                                    </button>
                                    <button class="btn btn-danger" data-toggle="modal" 
                                            data-target="#modalDecline${booking.id}">
                                            Decline request
                                    </button>  
                                    <a href="tour?id=${booking.tourId}" class="btn btn-info">Go to tour</a>
                                </c:if>
                            </div>
                            <c:if test="${booking.status!='Done' && booking.status!='Declined'}">
                            <div class="modal fade" id="modalRequest${booking.id}" tabindex="-1" role="dialog">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header text-center">
                                            <h4 class="modal-title w-100 font-weight-bold">Process this request</h4>
                                            <button type="button" class="close btn btn-danger" data-dismiss="modal" 
                                                    aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form action="handlebooking" method="POST" novalidate id="processForm${booking.id}" 
                                              class="needs-validation">
                                            <input type="hidden" name="id" value="${booking.id}">
                                            <div class="modal-body mx-3">
                                                <div class="md-form mb-2">
                                                    <label data-error="wrong" data-success="right" for="vehicle">
                                                        Assign a vehicle: 
                                                    </label>
                                                    <%--<fmt:parseDate value ="${booking.startDate}" var="temp" pattern="yyyy-MM-dd" />--%>
                                                    <%--<c:set var="end" value="${temp}" />--%>
                                                    <%--<c:set target="${end}" property="time" value="${end.time + 4*24*60*60*1000}" />--%>
                                                    <%--<fmt:formatDate var="end" value="${end}" pattern="yyyy-MM-dd" />--%>
                                                    <select id="vehicle" 
                                                           class="form-control validate" 
                                                           name="vehicle" required>
                                                        <c:forEach items="${vehicles}" var="vehicle">
                                                            <option value="${vehicle.ID}">
                                                                ${vehicle.type} - ${vehicle.driverPhone} - ${vehicle.maxPassenger} seats
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                    <div class="valid-feedback">Looks good!</div>
                                                    <div class="invalid-feedback">
                                                        Choose a vehicle!
                                                    </div>
                                                </div>
                                                <div class="md-form mb-2">
                                                    <label data-error="wrong" data-success="right" for="staff">
                                                        Assign a staff: 
                                                    </label>
                                                    <select id="staff" 
                                                           class="form-control validate" 
                                                           name="staff" required>
                                                        <c:forEach items="${staff}" var="staff">
                                                            <option value="${staff.id}">
                                                                ${staff.name} - ${staff.phone}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                    <div class="valid-feedback">Looks good!</div>
                                                    <div class="invalid-feedback">
                                                        Choose a staff!
                                                    </div>
                                                </div>
                                                <div class="md-form mb-2">
                                                    <label data-error="wrong" data-success="right" for="hotel">
                                                        Assign a hotel: 
                                                    </label>
                                                    <select id="hotel" 
                                                           class="form-control validate" 
                                                           name="hotel" required>
                                                        <c:forEach items="${hotels}" var="hotel">
                                                            <option value="${hotel.id}">
                                                                ${hotel.name} - ${hotel.location} - ${hotel.phone}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                    <div class="valid-feedback">Looks good!</div>
                                                    <div class="invalid-feedback">
                                                        Choose a hotel!
                                                    </div>
                                                </div>
                                                <div class="md-form mb-2">
                                                    <label data-error="wrong" data-success="right" for="restaurant">
                                                        Assign a restaurant: 
                                                    </label>
                                                    <select id="restaurant" 
                                                           class="form-control validate" 
                                                           name="restaurant" required>
                                                            <option>
                                                                1
                                                            </option>
                                                    </select>
                                                    <div class="valid-feedback">Looks good!</div>
                                                    <div class="invalid-feedback">
                                                        Choose a restaurant!
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer d-flex justify-content-center">
                                                <button class="btn btn-primary" type="submit" name="process">
                                                    Confirm
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="modal fade" id="modalDecline${booking.id}" tabindex="-1" role="dialog">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header text-center">
                                            <h4 class="modal-title w-100 font-weight-bold">Decline this request</h4>
                                            <button type="button" class="close btn btn-danger" data-dismiss="modal" 
                                                    aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form action="handlebooking" method="POST" novalidate id="declineform${booking.id}" 
                                              class="needs-validation">
                                            <input type="hidden" name="id" value="${booking.id}">
                                            <input type="hidden" min="1" name="index" value="${page.index}"> 
                                            <div class="modal-body mx-3">
                                                <div class="md-form mb-2">
                                                    <label data-error="wrong" data-success="right" for="reason">
                                                        Add reason
                                                    </label>
                                                    <textarea class="form-control validate" 
                                                              id="reason" rows="3" name="reason" 
                                                              maxlength="${300-fn:length(booking.note)}" required></textarea>
                                                    <div class="valid-feedback">Looks good!</div>
                                                    <div class="invalid-feedback">
                                                        Add some reason about your decline
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer d-flex justify-content-center">
                                                <button class="btn btn-primary" type="submit" name="decline">
                                                    Confirm
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <form action="bookinglist" method="POST" ${page.totalItems==0?'hidden':''}>
            <input type="hidden" min="1" name="index" value="${page.index}"> 
            <nav class="mt-4">
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <button type="submit" class="page-link" name="Prev" ${page.index==1?"hidden":""}>
                            <<
                        </button>
                    </li>
                    <li class="page-item ${page.index==1?"active":""}">
                        <button type="submit" class="page-link" name="first">1</button>
                    </li>
                    <li class="page-item disabled" ${page.totalPage<5?"hidden":""}>
                        <span class="page-link">...</span>
                    </li>
                    <c:if test="${page.totalPage>2}">
                        <c:forEach var="p" begin="${page.pageStart}" end="${page.pageEnd}">
                            <li class="page-item ${page.index==p?"active":""}">
                                <button type="submit" class="page-link" value="${p}" name="btnIdx">
                                    ${p}
                                </button>
                            </li>
                        </c:forEach>
                    </c:if>
                    <li class="page-item disabled" ${page.totalPage<5?"hidden":""}>
                        <span class="page-link">...</span>
                    </li>
                    <li class="page-item ${page.index==page.totalPage?"active":""}" ${page.totalPage==1?"hidden":""}>
                        <button type="submit" class="page-link"
                                name="last" value="${page.totalPage}">
                            ${page.totalPage}
                        </button>
                    </li>
                    <li class="page-item">
                        <button type="submit" class="page-link" name="Next" 
                                ${page.index==page.totalPage?"hidden":""}>
                            >>
                        </button>
                    </li>
                </ul>
            </nav>
        </form>
    </body>
    <script>
        (function () {
            'use strict';
            window.addEventListener('load', function () {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                let forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                let validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>
</html>
