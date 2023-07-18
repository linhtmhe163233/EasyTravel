<%-- 
    Document   : LandingPage
    Created on : 17/05/2023
    Author     : DucTM
    Update on  : 28/05/2023, implements some elements
    Update on  : 13/06/2023, adjust paging
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <title>Easy Travel | Home</title>
    </head>
    <body>
        <c:import url="./Layout/Header.jsp"></c:import>
        <img src="./images/home.jpg" class="img-fluid" style="width: 100vw; height: 45vh;">
        <div class="d-flex justify-content-end mr-5 mt-3">
            <form class="form-inline my-2 my-lg-0" action="home" method="get">
                <input name="search" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"
                           id="search" value="${param.search}">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
        <c:if test="${list.isEmpty()}">
            <h2 class="text-center mt-2">No tour found!</h2>
        </c:if>
        <div class="d-flex flex-wrap flex-row justify-content-start w-75 mx-auto mt-4" 
             style="column-gap: 8.5rem; row-gap: 2rem" id="deck">
            <c:forEach items="${list}" var="tour">
                <div class="card rounded" style="width: 18rem;">
                    <c:set var="link" value="tour?id=${tour.id}"></c:set>
                    <a href="${link}" data-toggle="tooltip" data-placement="top" title="Click to see details">
                        <img class="card-img-top border-bottom border-dark pb-4 rounded-top img-fluid"  
                             src="./images/${tour.image}" alt="${tour.name}" style="max-height: 216px">
                    </a>
                    <a href="${link}" class="card-body text-body card-link"  
                       data-toggle="tooltip" data-placement="top" title="Click to see details">
                        <h6 class="card-title text-truncate">${tour.name}</h6>
                        <p class="card-text border-bottom border-dark pb-2 text-truncate">${tour.destination}</p>
                        <p class="card-text">${fn:substring(tour.description, 0, 81)}...</p>
                    </a>
                    <div class="card-footer text-muted d-flex flex-row flex-wrap justify-content-between
                         align-items-center">
                        <c:if test="${tour.enabled}">
                            <a href="${link}&book=true" class="btn btn-primary">Book now</a>
                        </c:if>
                        <c:if test="${!tour.enabled}">
                            <button type="button" class="btn btn-danger disabled">Tour is closed!</button>
                        </c:if>
                        <p class="card-text text-right">
                            <fmt:formatNumber type="number" maxFractionDigits = "3" value="${tour.price}">
                            </fmt:formatNumber>
                        </p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <nav class="mt-4" ${page.totalItems==0?'hidden':''}>
            <ul class="pagination justify-content-center">
                <li class="page-item">
                    <a class="page-link" href="home?index=${page.index-1}" ${page.index==1?"hidden":""}>
                        <
                    </a>
                </li>
                <li class="page-item ${page.index==1?"active":""}">
                    <a class="page-link" href="home?index=1">1</a>
                </li>
                <li class="page-item disabled" ${page.totalPage<5?"hidden":""}>
                    <span class="page-link">...</span>
                </li>
                <c:if test="${page.totalPage>2}">
                    <c:forEach var="p" begin="${page.pageStart}" end="${page.pageEnd}">
                        <li class="page-item ${page.index==p?"active":""}">
                            <a class="page-link" href="home?index=${p}">
                                ${p}
                            </a>
                        </li>
                    </c:forEach>
                </c:if>
                <li class="page-item disabled" ${page.totalPage<5?"hidden":""}>
                    <span class="page-link">...</span>
                </li>
                <li class="page-item ${page.index==page.totalPage?"active":""}" ${page.totalPage==1?"hidden":""}>
                    <a class="page-link" href="home?index=${page.totalPage}">
                        ${page.totalPage}
                    </a>
                </li>
                <li class="page-item" ${page.index==page.totalPage?"hidden":""}>
                    <a class="page-link" href="home?index=${page.index+1}">
                        >
                    </a>
                </li>
            </ul>
        </nav>
    </body>
    <script>
        $('a.page-link').click((e) => {
            e.target.href += '&search=' + $('#search')[0].value;
        });
    </script>
</html>
