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
        <title>Easy Travel | Login</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
    </head
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light" style="background-color: #DDDDDD">
            <a class="navbar-brand" href="home">Home</a>
        </nav>
        <div id="login">
            <h3 class="text-center text-white pt-5">Login form</h3>
            <div class="container">
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" class="col-md-6">
                        <div id="login-box" class="col-md-12">
                            <form id="login-form" class="form" action="login" method="post">
                                <h3 class="text-center text-info">Login</h3>
                                <div class="form-group">
                                    <label for="username" class="text-info">Username:</label><br>

                                    <input type="text" name="username" id="username" class="form-control" placeholder="your username" required value="${param.username}">
                                    <p style="color:red">${message}</p> 

                                </div>
                                <div class="form-group">
                                    <label for="password" class="text-info">Password:</label><br>
                                    <input type="password" name="password" id="password" class="form-control " placeholder="Your password" required>
                                    <p style="color:red">${mess}</p>   
                                </div>
                                <div class="form-group">
                                    <input type="submit" name="submit" class="btn btn-info btn-md" value="Login">
                                </div>
                                <div id="register-link" class="text-right">
                                    <a href="/EasyTravel/register" class="text-info">Register</a><br>
                                    <a href="/EasyTravel/forgotpassword" class="text-info">Forgot password</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/jquery-3.7.0.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <c:import url="./Layout/Footer.jsp"></c:import>
    </body>
</html>
