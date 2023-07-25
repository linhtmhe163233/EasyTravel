<%-- 
    Document   : NewPassword
    Created on : Jun 20, 2023, 2:30:47 AM
    Author     : My Laptop
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Easy Travel | Check Code</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <style>
            .form-gap {
                padding-top: 150px;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light" style="background-color: #DDDDDD">
            <a class="navbar-brand" href="home">Home</a>
        </nav>
        <div class="form-gap"></div>
        <div class="mb-5"></div>
        <div class="row mt-5">
            <div class="col-md-6 col-sm-12 col-lg-6 col-md-offset-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">Check code</div>
                    <div class="panel-body">
                        <form name="myform" action="checkcode" method="post">
                            <div class="form-group">
                                <label for="code">Code:</label>
                                <input id="code" name="code" class="form-control" type="text" data-validation="required"
                                       required >
                                <span id="error_lastname" class="text-danger"></span>
                                <p style="color:red">${mess}</p>
                                <button id="update" type="submit" value="update" class="btn btn-primary center">Check</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="./Layout/Footer.jsp"></c:import>
    </body>
</html>
