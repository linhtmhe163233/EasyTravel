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
        <title>Easy Travel</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-3.7.0.js"></script>
    </head>
    <body>
        <c:import url="./Layout/Header.jsp"></c:import>
            <form class="w-25 mx-auto mt-5" action="changepassword" method="post">
                <div class="form-group">
                    <label for="crpassword">Current password<span class="text-danger"> *</span></label>
                    <input type="password" name="crpassword" class="form-control" id="crpassword"  
                           placeholder="Your current password"
                           data-validation="required"
                           required maxlength="50" minlength="8"
                           pattern="^[a-zA-z0-9]{8, 50}$">
                    <small style="color:red">${mess1}</small>
            </div>
            <div class="form-group">
                <label for="password">New password<span class="text-danger"> *</span></label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" 
                       placeholder="New password"
                       data-validation="required"
                       required maxlength="50" minlength="8"
                       pattern="^[a-zA-z0-9]{8, 50}$">
                <small style="color:red">${mess2}</small>
            </div>               
            <div class="form-group">
                <label for="cfpassword">Confirm password<span class="text-danger"> *</span></label>
                <input type="password" name="cfpassword" class="form-control" id="exampleInputPassword1" 
                       placeholder="Confirm password"
                       data-validation="required"
                       required maxlength="50" minlength="8"
                       pattern="^[a-zA-z0-9]{8, 50}$">
                <small style="color:red">${mess3}</small>
            </div>               
            <button type="submit" class="btn btn-primary">Update</button>
            <a href="/EasyTravel/profile" class="btn btn-secondary">Cancel</a>
        </form>
        <c:import url="./Layout/Footer.jsp"></c:import>
    </body>
</html>
