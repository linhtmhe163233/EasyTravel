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
        <title>Easy Travel | Home</title>
    </head>
    <body>
        <c:import url="./Layout/Header.jsp"></c:import>
            <div class="d-flex flex-wrap flex-row justify-content-start w-75 mx-auto mt-4" style="column-gap: 8.5rem; row-gap: 2rem">
            <c:forEach items="${list}" var="tour">
                <div class="card rounded" style="width: 18rem;">
                    <img class="card-img-top border-bottom border-dark pb-4 text-truncate rounded-top img-fluid" 
                         src="./images/${tour.image}" alt="${tour.name}" style="max-height: 216px">
                    <div class="card-body">
                        <h6 class="card-title text-truncate" title="${tour.name}">${tour.name}</h6>
                        <p class="card-text border-bottom border-dark pb-2 text-truncate">${tour.destination}</p>
                        <p class="card-text">${fn:substring(tour.description, 0, 81)}...</p>

                    </div>
                    <div class="card-footer text-muted d-flex flex-row flex-wrap justify-content-between align-items-center">
                        <a href="#" class="btn btn-primary">Book now</a>
                        <p class="card-text text-right">
                            <fmt:formatNumber type="number" maxFractionDigits = "3" value="${tour.price}">
                            </fmt:formatNumber>
                        </p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <form action="home" method="POST">
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
