<%-- 
    Document   : Erro
    Created on : May 17, 2023, 8:32:04 PM
    Author     : tranm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
        <c:import url="./Layout/Header.jsp"></c:import>
        <h1 class="text-center">${error}</h1>
        <div class="d-flex flex-row justify-content-center align-items-center">
            <a href="home" class="btn btn-secondary mr-4">Home</a>
            <a href="login" class="btn btn-primary">Login</a>   
        </div>
        <c:import url="./Layout/Footer.jsp"></c:import>
    </body>
</html>
