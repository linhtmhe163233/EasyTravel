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
            <h1 style =" margin-left: 200px;">LIST USERS</h1>
            <div class="d-flex justify-content-end mt-3 w-75 mx-auto">
                <form class="form-inline my-2 my-lg-0" action="usermanage" method="get">
                    <input name="search" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"
                           id="search" value="${param.search}">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
        <br>
        <table class="table table-hover table-bordered w-75 mx-auto" id="staffTable">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Fullname</th>
                    <th scope="col">Username</th>
                    <th scope="col">Email</th>
                    <th scope="col">DOB</th>
                    <th scope="col">Phone</th>
                    <th scope="col">Role</th>
                    <th scope="col" >Status</th>
                    <th scope="col" colspan="2"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="user" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.count+page.itemsPerPage*(page.index-1)}</th>
                        <td>${user.fullname}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.dob}</td>
                        <td>${user.phone}</td>
                        <td>${user.role}</td>
                        <td>${user.status}</td>
                <form action="usermanage" method="post">
                     <c:if test="${user.role!='Admin'}">
                    <td>
                        <input type="hidden" name="id" value="${user.id}">                   
                        <c:if test="${user.status=='Banned'}">
                            <input type="submit" value="Unban">
                            <input type="hidden" name="status" value="Active">   
                        </c:if>
                        <c:if test="${user.status!='Banned'}">  
                            <input type="submit" value="Ban">
                            <input type="hidden" name="status" value="Banned">
                        </c:if>
                    </td>
                     </c:if>
                </form>
                <!--                        <td><a href=# class="badge badge-info">Active</a></td>-->
                <!--                                                <td><a href="edit?HotelId=${hotel.id}" class="badge badge-info">Ban</a></td>-->
<!--                        <td><a href="#"  onclick="showMess(${hotel.id})" class="badge badge-danger">Ban</a></td>-->

            </tr>
        </c:forEach>
    </tbody>
</table>
<nav class="mt-4" ${page.totalItems==0?'hidden':''}>
    <ul class="pagination justify-content-center">
        <li class="page-item">
            <a class="page-link" href="usermanage?index=${page.index-1}" ${page.index==1?"hidden":""}>
                <
            </a>
        </li>
        <li class="page-item ${page.index==1?"active":""}">
            <a class="page-link" href="usermanage?index=1">1</a>
        </li>
        <li class="page-item disabled" ${page.totalPage<5?"hidden":""}>
            <span class="page-link">...</span>
        </li>
        <c:if test="${page.totalPage>2}">
            <c:forEach var="p" begin="${page.pageStart}" end="${page.pageEnd}">
                <li class="page-item ${page.index==p?"active":""}">
                    <a class="page-link" href="usermanage?index=${p}">
                        ${p}
                    </a>
                </li>
            </c:forEach>
        </c:if>
        <li class="page-item disabled" ${page.totalPage<5?"hidden":""}>
            <span class="page-link">...</span>
        </li>
        <li class="page-item ${page.index==page.totalPage?"active":""}" ${page.totalPage==1?"hidden":""}>
            <a class="page-link" href="usermanage?index=${page.totalPage}">
                ${page.totalPage}
            </a>
        </li>
        <li class="page-item" ${page.index==page.totalPage?"hidden":""}>
            <a class="page-link" href="usermanage?index=${page.index+1}">
                >
            </a>
        </li>
    </ul>
</nav>
<c:import url="../Layout/Footer.jsp"></c:import>
</body>
<script src="js/jquery-3.7.0.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>
    $('a.page-link').click((e) => {
        e.target.href += '&search=' + $('#search')[0].value;
    });
</script>
</html>
