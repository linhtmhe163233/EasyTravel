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
        <title>EasyTravel</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>


        <!--        <style>
                    .palel-primary
                    {
                        border-color: #bce8f1;
                    }
                    .panel-primary>.panel-heading
                    {
                        background:#bce8f1;
        
                    }
                    .panel-primary>.panel-body
                    {
                        background-color: #EDEDED;
                    }
                </style>-->
        
    </head>

    <body>
        <div class="mb-5"></div>
    <div class="row mt-5">
        <div class="col-md-6 col-sm-12 col-lg-6 col-md-offset-3">
            <div class="panel panel-primary">
                <div class="panel-heading">Change password
                </div>

                <div class="panel-body">
                    <form name="myform" action="changepassword" method="post">

                        <div class="form-group">
                            <label for="crpassword">Current password</label>
                            <input id="crpassword" name="crpassword" class="form-control" type="password" data-validation="required" required>
<!--                                <intput type="hidden" name="id" value="${sessionScope.user.id}"/>-->
                            <p style="color:red">${mess1}</p>   
<!--                                <p style="color:red">${oldpassword}</p>   
                            <p style="color:red">${crpassword}</p>   -->
                            <span id="error_name" class="text-danger"></span>
                        </div>
                        <div class="form-group">
                            <label for="newpassword">New password</label>
                            <input id="newpassword" name="password" class="form-control" type="password" data-validation="required"
                                   required maxlength="50" minlength="8"
                                   pattern="^[a-zA-z0-9]{8, 50}$">
                            <p style="color:red">${mess2}</p>   
<!--                                <p style="color:red">${password}</p>   -->
                            <span id="error_lastname" class="text-danger"></span>
                        </div>
                        <div class="form-group">
                            <label for="age">Confirm password</label>
                            <input id="cfpassword" name="cfpassword"  class="form-control" type="password" data-validation="required"
                                   required maxlength="50" minlength="8"
                                   pattern="^[a-zA-z0-9]{8, 50}$">
                            <p style="color:red">${mess3}</p>   
<!--                                <p style="color:red">${cfpassword}</p>  -->
                            <span id="error_age" class="text-danger"></span>
                        </div>                            

                        <button id="update" type="submit" value="update" class="btn btn-primary center">Update</button>
                        <a href="/EasyTravel/profile" class="btn btn-primary center">Cancel</a>

                    </form>

                </div>
            </div>
        </div>
    </div>

</body>
<!--    <script>
        $(document).ready(function () {
            $('.pass_show').append('<span class="ptxt">Show</span>');
        });


        $(document).on('click', '.pass_show .ptxt', function () {

            $(this).text($(this).text() == "Show" ? "Hide" : "Show");

            $(this).prev().attr('type', function (index, attr) {
                return attr == 'password' ? 'text' : 'password';
            });

        });
    </script>-->


</html>
