<%-- 
    Document   : LandingPage
    Created on : May 31, 2023
    Author     : DucTM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="EasyTravel">
        <meta name="keywords" content="travel">
        <meta name="author" content="Group6">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link href="css/fontawesome.min.css" rel="stylesheet"/>
        <title>EasyTravel | Staff</title>
    </head>
    <body>
        <c:import url="../Layout/Header.jsp"></c:import>
            <div class="">
                <h1 class="text-center">Staff</h1>
                <button class="btn btn-primary btn-rounded mr-4 float-right" 
                        data-toggle="modal" data-target="#modalAddStaff" id="add">
                    Add
                </button>
            </div>
            <table class="table table-hover table-bordered w-75 mx-auto" id="staffTable">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Phone</th>
                        <th scope="col">DOB</th>
                        <th scope="col">Gender</th>
                        <th scope="col" colspan="2"></th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="staff" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.count+page.itemsPerPage*(page.index-1)}</th>
                        <td>${staff.name}</td>
                        <td>${staff.phone}</td>
                        <td>${staff.DOB}</td>
                        <td>${staff.gender?"Male":"Female"}</td>
                        <td><a href="#" class="badge badge-info">Edit</a></td>
                        <td><a href="#" class="badge badge-danger">Delete</a></td>
                    </tr> 
                </c:forEach>
            </tbody>
        </table>
        <form action="staff" method="POST">
            <input type="hidden" min="1" name="index" value="${page.index}"> 
            <nav class="mt-4">
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <button type="submit" class="page-link" name="Prev" ${page.index==1?"hidden":""}>
                            <<
                        </button>
                    </li>
                    <li class="page-item ${page.index==1?"active":""}">
                        <button type="submit" class="page-link" name="first">1</button>
                    </li>
                    <li class="page-item disabled" ${page.totalPage<5?"hidden":""}>
                        <span class="page-link">...</span>
                    </li>
                    <c:if test="${page.totalPage>2}">
                        <c:forEach var="p" begin="${page.pageStart}" end="${page.pageEnd}">
                            <li class="page-item ${page.index==p?"active":""}">
                                <button type="submit" class="page-link" value="${p}" name="btnIdx">
                                    ${p}
                                </button>
                            </li>
                        </c:forEach>
                    </c:if>
                    <li class="page-item disabled" ${page.totalPage<5?"hidden":""}>
                        <span class="page-link">...</span>
                    </li>
                    <li class="page-item ${page.index==page.totalPage?"active":""}" ${page.totalPage==1?"hidden":""}>
                        <button type="submit" class="page-link"
                                name="last" value="${page.totalPage}">
                            ${page.totalPage}
                        </button>
                    </li>
                    <li class="page-item">
                        <button type="submit" class="page-link" name="Next" 
                                ${page.index==page.totalPage?"hidden":""}>
                            >>
                        </button>
                    </li>
                </ul>
            </nav>
        </form>
        <div class="modal fade" id="modalAddStaff" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h4 class="modal-title w-100 font-weight-bold">Add a new staff</h4>
                        <button type="button" class="close btn btn-danger" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="/EasyTravel/staff" method="POST" novalidate id="form" class="needs-validation">
                        <input type="hidden" min="1" name="index" value="${page.index}"> 
                        <div class="modal-body mx-3">
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="name">Staff name</label>
                                <input type="text" id="name" class="form-control validate" name="name" required maxlength="80"
                                       pattern="^\s*\p{L}+(\s\p{L}+)*\s*$" value="${staff.name}">
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Name must be fewer than 80 letters and spaces and not empty!</div>
                            </div>
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="phone">Phone number</label>
                                <input type="text" id="phone" class="form-control validate" name="phone" required 
                                       pattern="^0[0-9]{9}$" value="${staff.phone}">
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Phone number can only contain 10 digits! Ex:0123456789</div>
                                <c:if test="${message!=null}">
                                    <div class="text-danger">Phone must be unique!</div>
                                </c:if>
                            </div>
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="dob">Date of birth</label>
                                <input type="date" id="dob" class="form-control" name="DOB" required value="${staff.DOB}">
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Staff must be over 18 years old</div>
                            </div>
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="gender">Gender</label>
                                <select name="gender" class="form-control" id="gender" required>
                                    <option ${staff.gender?"selected":""}>Male</option>
                                    <option ${staff.gender?"":"selected"}>Female</option>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer d-flex justify-content-center">
                            <button class="btn btn-primary" type="submit" name="add">Confirm</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script src="js/jquery-3.7.0.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/StaffList.js"></script>
    <script>
        let message = '${requestScope.message}';
        if (message !== '') {
            document.getElementById("add").click();
        }
    </script>
</html>
