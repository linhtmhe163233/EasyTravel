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
        <title>EasyTravel | Tour History</title>
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
                            <div class="col-10">
                                <b>Note: </b>${booking.note}
                                <c:if test="${booking.reason!=null}">
                                    <br>
                                    <b>Reason for cancel:  </b>${booking.reason}
                                </c:if>
                            </div>
                            <div class="col-2">
                                <a href="tour?id=${booking.tourId}" class="btn btn-info">Go to tour</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>        
        <form action="history" method="POST" ${page.totalItems==0?'hidden':''}>
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
</html>
