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

        <script src="js/jquery-3.7.0.js" type="text/javascript"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <link href="css/fontawesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://use.fontawesome.com/releases/v5.0.11/css/all.css" rel="stylesheet">
    </head>
    <body>
       
        <div class="container">
            <h2 class="">&nbsp</h2>
            <div class="row">
                <div class="col-12 col-md-8 col-lg-6 pb-5 mx-auto">


                    <!--Form with header-->

                    <form action="register" method="post" id="form">
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
                                        <input type="text" class="form-control" id="fullname" name="fullname" 
                                               placeholder="Full Name" required pattern="^\s*\p{L}+(\s\p{L}+)*\s*$"
                                               maxlength="80"></input>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text"><i class="fa fa-user text-info"></i></div>
                                        </div>
                                        <input type="text" class="form-control" id="username" name="username" 
                                               placeholder="User Name" required pattern="^[a-zA-z0-9]+$" maxlength="50"></input>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text"><i class="fa fa-user text-info"></i></div>
                                        </div>
                                        <input type="password" class="form-control" id="password" name="password" 
                                               placeholder="Your Password" required maxlength="50" minlength="8"
                                               pattern="^[a-zA-z0-9]{8, 50}$"></input>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text"><i class="fa fa-user text-info"></i></div>
                                        </div>
                                        <input type="password" class="form-control" id="cfpassword" name="cfpassword" 
                                               placeholder="Confirm password"  maxlength="50" minlength="8"
                                               required pattern="^[a-zA-z0-9]{8, 50}$"></input>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text"><i class="fa fa-envelope text-info"></i></div>
                                        </div>
                                        <input type="email" class="form-control" id="email" name="email" 
                                               placeholder="example@gmail.com" required maxlength=80></input>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text"><i class="fa fa-user text-info"></i></div>
                                        </div>
                                        <input type="text" class="form-control" id="phone" name="phone" 
                                               placeholder="Your phone number" required pattern="^0[0-9]{9}$"></input>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">Role</div>
                                        </div>
                                        <select class="form-control"  name="role" id="role">
                                            <option value="Tourist">Tourist</option>
                                            <option value="Travel Agent">Travel agent</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">Date of birth</div>
                                        </div>
                                        <input type="date" class="form-control" id="dob" name="dob" 
                                               placeholder="Your birthday" required></input>
                                    </div>
                                </div>                                

                                <div class="text-center">
                                    <input type="submit" value="Register" 
                                           class="btn btn-info btn-block rounded-0 py-2">

                                    </input>
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
    <script>
        Date.prototype.addYears = function (years) {
            let date = new Date(this);
            date.setYear(date.getFullYear() + years);
            return date;
        };
        let dob = document.getElementById("dob");
        dob.max = new Date().addYears(-18).toISOString().split("T")[0];

        let form = document.getElementById("form");
        let password = document.getElementById("password");
        let cfpassword = document.getElementById("cfpassword");
        form.oninput = () => {
            cfpassword.setCustomValidity(cfpassword.value !== password.value ? "Passwords do not match." : "");
        };
    </script>
</html>
