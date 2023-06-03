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
    </head>
    <body>
        <h1>Login</h1>
        <form action="/EasyTravel/login" method="post">
            Username: <input type="text" name ="username"/><br><!-- comment -->
            Password: <input type="text" name ="password"/><br>
            <input type="submit" value="Login"/><br>
            <% request.getContextPath()%>/login
            <a href="/EasyTravel/register">Register</a><br>
            <a href="/EasyTravel/register">Forgot password</a>
        </form>
    </body>
</html>
