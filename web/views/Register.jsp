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
        <title>Easy Travel | Register</title>

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <link href="https://use.fontawesome.com/releases/v5.0.11/css/all.css" rel="stylesheet">
    </head>
    <style>
        /*        h1{
                    text-transform: uppercase;
                    text-align: center;
                    color: black;
                    font-family: arial;
                }
                form{
                    text-align: center;
        
                }
                table{
                    text-align: center;
                }
                p{
                    text-align: center;
                }*/
    </style>
    <body>
        <!--        <h1>Register</h1>
               
                <form action="register" method="post">
                    Full name: <input type="text" name="fullname"><br>
                    Username: <input type="text" name="username"><br>
                    Password: <input type="password" name="password"/><br>
                    Confirm password: <input type="password" name="cfpassword"/><br>
                    Email:<input type="text" name="email"/><br>
                    Phone number: <input type="text" name="phone" /><br>
                    Role: <select name="role">
                        <option value="tourist">Tourist</option>
                        <option value="travel agent">Travel agent</option>
                    </select><br>
                    Date of birth: <input type="date" name="dob"><br>
                    <input type="submit" value="Register">
                </form>-->


        <div class="container">
            <h2 class="">&nbsp</h2>
            <div class="row">
                <div class="col-12 col-md-8 col-lg-6 pb-5">


                    <!--Form with header-->

                    <form action="register" th:action="@{/addUser}" th:object="${user}" method="post">
                        <div class="card border-primary rounded-0">
                            <div class="card-header p-0">
                                <div class="bg-info text-white text-center py-2">
                                    <h3><i class="fa fa-envelope"></i> Register</h3>
                                </div>
                            </div>
                            <div class="card-body p-3">

                                <!--Body-->
                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text"><i class="fa fa-user text-info"></i></div>
                                        </div>
                                        <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Full Name" required th:field="*{fullname}"></input>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text"><i class="fa fa-user text-info"></i></div>
                                        </div>
                                        <input type="text" class="form-control" id="username" name="username" placeholder="User Name" required th:field="*{username}"></input>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text"><i class="fa fa-user text-info"></i></div>
                                        </div>
                                        <input type="password" class="form-control" id="password" name="password" placeholder="Your Password" required th:field="*{password}"></input>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text"><i class="fa fa-user text-info"></i></div>
                                        </div>
                                        <input type="password" class="form-control" id="cfpassword" name="cfpassword" placeholder="Confirm password" required th:field="*{cfpassword}"></input>
                                    </div>
                                    <p style="color:red">${messpass}</p>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text"><i class="fa fa-envelope text-info"></i></div>
                                        </div>
                                        <input type="email" class="form-control" id="email" name="email" placeholder="example@gmail.com" required th:field="*{email}"></input>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text"><i class="fa fa-user text-info"></i></div>
                                        </div>
                                        <input type="text" class="form-control" id="phonenumber" name="phonenumber" placeholder="Your phone number" required th:field="*{phonenumber}"></input>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">Role</div>
                                        </div>
                                        <select class="form-control"  name="role">
                                            <option value="tourist">Tourist</option>
                                            <option value="travel agent">Travel agent</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">Date of birth</div>
                                        </div>
                                        <input type="date" class="form-control" id="dob" name="dob" placeholder="Your birthday" required th:field="*{dob}"></input>
                                    </div>
                                </div>                                

                                <div class="text-center">
                                    <input type="submit" value="Register" class="btn btn-info btn-block rounded-0 py-2"></input>
                                </div>
                                <div class="text-center">
                                    <a href="/EasyTravel/login" class="text-info">Sign in</a>
                                </div>
                            </div>

                        </div>
                    </form>
                    <!--Form with header-->


                </div>
            </div>
        </div>


    </body>
</html>
