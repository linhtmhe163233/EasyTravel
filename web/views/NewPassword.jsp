<%-- 
    Document   : NewPassword
    Created on : Jun 20, 2023, 2:30:47 AM
    Author     : My Laptop
--%>

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
                <div class="panel-heading">New password
                </div>

                <div class="panel-body">
                    <form name="myform" action="newpassword" method="post">

                      
                        <div class="form-group">
                            <label for="newpassword">New password</label>
                            <input id="newpassword" name="password" class="form-control" type="password" data-validation="required"
                                   required maxlength="50" minlength="8"
                                   pattern="^[a-zA-z0-9]{8, 50}$">
                          
                            <span id="error_lastname" class="text-danger"></span>
                        </div>
                        <div class="form-group">
                            <label for="age">Confirm password</label>
                            <input id="cfpassword" name="cfpassword"  class="form-control" type="password" data-validation="required"
                                   required maxlength="50" minlength="8"
                                   pattern="^[a-zA-z0-9]{8, 50}$">
                            <p style="color:red">${mess}</p>   

                            <span id="error_age" class="text-danger"></span>
                        </div>                            

                        <button id="update" type="submit" value="update" class="btn btn-primary center">Update</button>
                        <a href="/EasyTravel/login" class="btn btn-primary center">Cancel</a>

                    </form>

                </div>
            </div>
        </div>
    </div>

</body>
</html>
