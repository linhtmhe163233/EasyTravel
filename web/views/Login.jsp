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
        <style>
        h1{
            text-transform: uppercase;
            text-align: center;
            color: black;
            font-family: arial;
        }
        form{
            text-align: center;
        }
    </style>
    </head
    
    <body>
        <h1>Login</h1>
        <form action="/EasyTravel/login" method="post">
            Username: <input type="text" name ="username" ><br><!-- comment -->
            Password: <input type="password" name ="password"/><br>
            <p style="color:red">${mess}</p>            
            <input type="submit" value="Login"/><br>
            <a href="/EasyTravel/register">Register</a><br>
            <a href="/EasyTravel/register">Forgot password</a>
        </form>
       
    </body>
</html>
