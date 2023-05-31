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
    </head>
    <body>
        <h1>Register</h1>
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
        <input type="submit" name="submit">
        </form>
    </body>
</html>
