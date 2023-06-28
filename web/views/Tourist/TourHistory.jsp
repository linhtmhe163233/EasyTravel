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
        <script src="js/jquery-3.7.0.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <title>EasyTravel | Tour History</title>
    </head>
    <body>
        <c:import url="../Layout/Header.jsp"></c:import>
        <table class="table table-hover w-75 mx-auto">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Book time</th>
                    <th scope="col">Tour name</th>
                    <th scope="col">Start date</th>
                    <th scope="col">Tourists quantity</th>
                    <th scope="col">Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="booking" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.count+page.itemsPerPage*(page.index-1)}</th>
                        <td>${booking.bookTime}</td>
                        <td><a href="tour?id=${booking.tourId}" class="badge badge-info">${booking.tourName}</a></td>
                        <td>${booking.startDate}</td>
                        <td>${booking.touristsQuantity}</td>
                        <td class="${booking.status=='Processing'?'text-warning':
                                     booking.status=='Ready'?'text-primary':'text-success'}">${booking.status}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form action="history" method="POST">
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
