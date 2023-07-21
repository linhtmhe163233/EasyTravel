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
        <title>Easy Travel | Restaurant</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link href="css/fontawesome.min.css" rel="stylesheet"/>
    </head>
    <body>
        <c:import url="../Layout/Header.jsp"></c:import>
        <c:if test="${param['index']==null }">   
            <c:set var = "index" scope = "page" value = "1"/>
        </c:if>
        <c:if test="${param['index']!=null}">
            <c:set var = "index" scope = "page" value = "${param['index']}"/>
        </c:if>
        <div class="title mt-5">
            <h1 style="text-align: center;">View a list Restaurant</h1>
        </div>
        <form  method="get" action="RestaurantList" class="search  w-75 mx-auto " style="display: flex; justify-content: space-between;width: 100%;padding-left: 105px;">
            <div>
                <a class="btn btn-primary" href="CreateRestaurant">Create</a>
            </div>
            <div>
                <input type="text" placeholder="Search" name="search by type or phone" value="${param.search}"/>
                <input type="hidden" value="${param.index}" name="index"/>
                <button type="submit" class="btn btn-primary" >Search</button>
            </div>
        </form>
        <div class="mt-5">
            <table  class="table table-hover table-bordered w-75 mx-auto" >
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Type</th>
                        <th scope="col">Table Available</th>
                        <th scope="col">Phone Number</th>
                        <th scope="col">Agent Name</th>
                        <th colspan="2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="a" items="${list}">
                        <tr>
                            <th scope="row">${a.getId()}</th>
                            <td>${a.getType()}</td>
                            <td>${a.getTable_available()}</td>
                            <td>${a.getPhone()}</td>
                            <td>${a.getAgentName()}</td>    
                            <td><a href="EditRestau?rid=${a.getId()}" class="btn btn-info btn-sm"> Edit</a></td>
                            <td><a href="DeleteRestau?rid=${a.getId()}" class="btn btn-danger btn-sm"> Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="pagination-arena" style="margin-left: 45%;">
                <ul class="pagination">
                    <li class="page-item"><a href="./RestaurantList?index=1&search=${param['search']}" class="page-link"><<</a></li>
                    <li class="page-item">
                        <a href="./RestaurantList?index=${index-2}&search=${param['search']}" class="page-link ${index-2<1?"d-none":""}">${index-2}</a></li>
                    <li class="page-item">
                        <a href="./RestaurantList?index=${index-1}&search=${param['search']}" class="page-link ${index-1<1?"d-none":""}">${index-1}</a></li>
                    <li class="page-item active">
                        <a href="./RestaurantList?index=${index}&search=${param['search']}" class="page-link">${index}</a></li>
                    <li class="page-item">
                        <a href="./RestaurantList?index=${index+1}&search=${param['search']}" class="page-link ${index+1>numberPage?"d-none":""}" >${index+1}</a></li>
                    <li class="page-item">
                        <a href="./RestaurantList?index=${index+2}&search=${param['search']}" class="page-link ${index+2>numberPage?"d-none":""}">${index+2}</a></li>
                    <li><a href="./RestaurantList?index=${numberPage}&search=${param['search']}" class="page-link">>></a></li>
                </ul>
            </div> 
        </div>
        <c:import url="../Layout/Footer.jsp"></c:import>
    </body>
</html>
