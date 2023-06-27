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

        <meta name="description" content="EasyTravel">
        <meta name="keywords" content="travel">
        <meta name="author" content="Group6">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link href="css/fontawesome.min.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EasyTravel | Staff</title>


    </head>
    <body>
        <c:import url="../Layout/Header.jsp"></c:import>
            <table class="table table-hover table-bordered w-75 mx-auto" id="staffTable">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Fullname</th>
                        <th scope="col">Username</th>
                        <th scope="col">Email</th>
                        <th scope="col">DOB</th>
<!--                        <th scope="col">Email</th>-->
                        <th scope="col">Phone</th>
                        <th scope="col">Role</th>
<!--                        <th scope="col">Status</th>-->
<!--                        <th scope="col" colspan="2"></th>-->
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="user" varStatus="loop">

                    <tr>
<!--                        <th scope="row">${user.id}</th>-->
                        <th scope="row">${loop.count+page.itemsPerPage*(page.index-1)}</th>
                        <td>${user.fullname}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.dob}</td>
<!--                        <td>${user.email}</td>-->
                        <td>${user.phone}</td>
                        <td>${user.role}</td>
                        
<!--                        <td><a href=# class="badge badge-info">Active</a></td>-->
<!--                        <td><a href="edit?HotelId=${hotel.id}" class="badge badge-info">Active</a></td>-->
<!--                        <td><a href="#"  onclick="showMess(${hotel.id})" class="badge badge-danger">Delete</a></td>-->

                    </tr>
                </c:forEach>
            </table>
            </tbody>
            <form action="manageuser" method="post">
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
    <script src="js/jquery-3.7.0.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/StaffList.js"></script>
   
</html>
