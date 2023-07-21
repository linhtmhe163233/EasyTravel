<%-- 
    Document   : ForgotPassword
    Created on : Jun 19, 2023, 9:09:31 AM
    Author     : linhtm
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery-3.7.0.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <style>
            .form-gap {
                padding-top: 70px;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light" style="background-color: #DDDDDD">
            <a class="navbar-brand" href="home">Home</a>
        </nav>
        <div class="form-gap"></div>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="text-center">
                            <h3><i class="fa fa-lock fa-4x"></i></h3>
                            <h2 class="text-center">Forgot Password?</h2>
                            <p>You can reset your password here.</p>
                            <p style="color:red">${mess}</p>
                            <div class="panel-body">

                                <form method="post" action="forgotpassword">

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                                            <input id="email" name="email" placeholder="email address" class="form-control"  type="email" required value="${param.email}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Reset Password" type="submit">
                                    </div>
                                    <div >
                                        <a href="/EasyTravel/register" class="text-info">Register</a><br>
                                        <a href="/EasyTravel/login" class="text-info">Login</a>
                                    </div>
                                    <input type="hidden" class="hide" name="token" id="token" value=""> 
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="./Layout/Footer.jsp"></c:import>
    </body>
</html>
