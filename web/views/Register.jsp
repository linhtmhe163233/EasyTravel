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
        table{
            text-align: center;
        }
    </style>
    <body>
        <h1>Register</h1>
        <form action="register" method="post">
        <table >

            <tbody>
                <tr>
                    <td>Full name:</td>
                    <td><input type="text" name="fullname"></td>
                </tr>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>Confirm password:</td>
                    <td><input type="password" name="cfpassword"/></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="email" name="email"/></td>
                </tr>
                <tr>
                    <td>Phone number</td>
                    <td><input type="text" name="phone" /></td>
                </tr>
                <tr>
                    <td>Role</td>
                    <td>
                        <select name="role">
                            <option value="tourist">Tourist</option>
                            <option value="travel agent">Travel agent</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Date of birth</td>
                    <td><input type="date" name="dob"></td>
                </tr>
            </tbody>
        </table>
            <input type="submit" name="submit">
        </form>
<!--        <form action="register" method="post">
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
        </form>-->


<p style="color:red">${messpass}</p>
    </body>
</html>
