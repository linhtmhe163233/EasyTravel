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
        <style>
            .title{
                text-align: center;
                width: 20%;
                display: flex;
                justify-content: center;
                border-radius: 20px;
                margin-left: 40%;
            }

            input{
                height: 40px;
                padding-bottom: 8px;
                border-radius: 10px;
            }
            select{
                width: 190px;
                height: 40px;
                padding-bottom: 8px;
                border-radius: 10px;
            }
            .search{
                float: right;
                margin-right: 30px;
            }
            button{
                background: #3DE397 !important;
            }
            .table{
                width: 80%;
                margin-left: 200px;
            }
            tr{
                border-bottom: none;
            }
            td{
                border-bottom: none;
            }
            .word{
                font-weight: 500;
                font-size: 25px;
            }
            a{
                color: white;
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <c:import url="../Layout/Header.jsp"></c:import>

            <div class="title mt-5">
                <h1>Add a Restaurant</h1>
            </div>

            <form action="CreateRestaurant" method="post">
                <div class="mt-5">
                    <table class="table">
                        <tbody>
                            <tr>
                                <td class="word">Type</td>
                                <td><input type="text" name="type" maxlength="30" required value="${type}"/></td>
                            <td class="word">Phone</td>
                            <td><input type="text"  name="phone" maxlength="10" minlength="10" value="${phone}" required/></td>
                        </tr>
                        <tr>
                            <!--                            <td class="word">Name Agent</td>
                                                        <td>
                                                            <select name="name">
                            <c:forEach var="a" items="${al}">
                                <option value="${a.id}">${a.fullname}</option>
                            </c:forEach>
                        </select>
                    </td>-->
                            <td class="word">Table Available</td>
                            <td><input type="number" min="1" name="table" value="${table}" /></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="text-center">
                <span style="color: red;">${msg}</span>
            </div>
            <div class="text-center" style="margin-top: 120px;">
                <button class="btn" type="submit">Create</button>
            </div>
        </form>
        <c:import url="../Layout/Footer.jsp"></c:import>
    </body>
</html>
