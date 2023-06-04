<%-- 
    Document   : LandingPage
    Created on : May 17, 2023, 8:05:10 PM
    Author     : tranm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Easy Travel | Login</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<!--        <style>
            /*            h1{
                            text-transform: uppercase;
                            text-align: center;
                            color: black;
                            font-family: arial;
                        }*/
        
        </style>-->
    </head

    <body>
        <!--            <h1>Login</h1>
                    <form action="/EasyTravel/login" method="post">
                        Username: <input type="text" name ="username" ><br> comment 
                        Password: <input type="password" name ="password"/><br>
                        <p style="color:red">${mess}</p>            
                            <input type="submit" value="Login"/><br>
                            <a href="/EasyTravel/register">Register</a><br>
                            <a href="/EasyTravel/register">Forgot password</a>
                        </form>-->

        <div id="login">
            <h3 class="text-center text-white pt-5">Login form</h3>
            <div class="container">
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" class="col-md-6">
                        <div id="login-box" class="col-md-12">
                            <form id="login-form" class="form" action="" method="post">
                                <h3 class="text-center text-info">Login</h3>
                                <div class="form-group">
                                    <label for="username" class="text-info">Username:</label><br>
                                    <input type="text" name="username" id="username" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="password" class="text-info">Password:</label><br>
                                    <input type="password" name="password" id="password" class="form-control">
                                    <p style="color:red">${mess}</p>   
                                </div>
                                <div class="form-group">

                                    <input type="submit" name="submit" class="btn btn-info btn-md" value="Login">
                                </div>
                                <div id="register-link" class="text-right">
                                    <a href="/EasyTravel/register" class="text-info">Register</a><br>
                                    <a href="/EasyTravel/register" class="text-info">Forgot password</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </body>
</html>
