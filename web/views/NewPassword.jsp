<%-- 
    Document   : NewPassword
    Created on : Jun 23, 2023, 11:59:40 AM
    Author     : My Laptop
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-3.7.0.js"></script>
    </head>
    <body>


        <!--                    <form class="w-25 mx-auto" action="newpassword" method="post">
                                
                            <div class="form-group">
                                <label for="password">New password</label>
                                <input type="password" name="password" class="form-control" id="exampleInputPassword1" 
                                       placeholder="New password"
                                       data-validation="required"
                                       required maxlength="50" minlength="8"
                                       pattern="^[a-zA-z0-9]{8, 50}$">
                                
                            </div>               
                            <div class="form-group">
                                <label for="cfpassword">Confirm password</label>
                                <input type="password" name="cfpassword" class="form-control" id="exampleInputPassword1" 
                                       placeholder="Confirm password"
                                       data-validation="required"
                                       required maxlength="50" minlength="8"
                                       pattern="^[a-zA-z0-9]{8, 50}$">
                                <small style="color:red">${mess}</small>
                            </div>               
                            <button type="submit" class="btn btn-primary">Update</button>
                            <a href="#" class="btn btn-secondary">Cancel</a>
                
                        </form>-->

        <c:import url="./Layout/Header.jsp"></c:import>

            <form class="w-25 mx-auto" action="newpassword" method="post">
                <div class="form-group">
                    <label for="password">New password<span class="text-danger"> *</span></label>
                    <input type="password" name="password" class="form-control" id="password"  
                           placeholder="Your new password"
                           data-validation="required"
                           required maxlength="50" minlength="8"
                           pattern="^[a-zA-z0-9]{8, 50}$">

                </div>
                <div class="form-group">
                    <label for="cfpassword">Confirm password<span class="text-danger"> *</span></label>
                    <input type="password" name="cfpassword" class="form-control" id="cfpassword" 
                           placeholder="New password"
                           data-validation="required"
                           required maxlength="50" minlength="8"
                           pattern="^[a-zA-z0-9]{8, 50}$">
                    <small style="color:red">${mess}</small>
            </div>          

            <button type="submit" class="btn btn-primary">Update</button>
            <a href="/EasyTravel/forgotpassword" class="btn btn-secondary">Cancel</a>

        </form>
    </body>
</html>
