<%-- 
    Document   : LandingPage
    Created on : 17/05/2023
    Author     : DucTM
    Update on  : 16/06/2023, implement update button
    update on  : 18/06/2023, implement disable/enable
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
        <script src="js/jquery-3.7.0.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
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
                    <% request.getSession().setAttribute("tour", request.getAttribute("tour")); %>
                    <form action="tour" method="post">
                        <a href="tours?act=update" type="button" class="btn btn-info">
                            Update
                        </a>
                        <input type="hidden" value="${tour.id}" name="id">
                        <c:if test="${tour.enabled}">
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#disableModal">
                                Disable
                            </button>
                            <div class="modal fade" id="disableModal" tabindex="-1" role="dialog" 
                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Are you sure?</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            Tourists can't access a disabled tour but you can undo this action anytime.
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-danger" name="disable">Disable tour</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${!tour.enabled}">
                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#enableModal">
                                Enable
                            </button>
                            <div class="modal fade" id="enableModal" tabindex="-1" role="dialog" 
                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Are you sure?</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            Tourists can access this tour again.
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-success" name="enable">Enable tour</button>
                                        </div>
                                    </div>
                                </div>
                            </div>     

                        </c:if>
                    </form>
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
