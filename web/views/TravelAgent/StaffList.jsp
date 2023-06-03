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
        <div class="">
            <h1 class="text-center">Staff</h1>
            <button class="btn btn-primary btn-rounded mr-4 float-right" 
                    data-toggle="modal" data-target="#modalAddStaff">
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
                <c:forEach items="${list}" var="staff">
                    <tr>
                        <th scope="row">${staff.ID}</th>
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
        <div class="modal fade" id="modalAddStaff" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h4 class="modal-title w-100 font-weight-bold">Add a new staff</h4>
                        <button type="button" class="close btn btn-danger" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="/staff" method="POST" novalidate id="form" class="needs-validation">
                        <div class="modal-body mx-3">
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="name">Staff name</label>
                                <input type="text" id="name" class="form-control validate" name="name" required maxlength="80">
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Name must be fewer than 80 characters and not empty!</div>
                            </div>
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="phone">Phone number</label>
                                <input type="text" id="phone" class="form-control validate" name="phone" required>
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Phone number can only contain 10 digits! ex:0123456789</div>
                            </div>
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="dob">Date of birth</label>
                                <input type="date" id="dob" class="form-control" name="DOB" required>
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Staff must be over 18 years old</div>
                            </div>
                            <div class="md-form mb-5">
                                <label data-error="wrong" data-success="right" for="gender">Gender</label>
                                <select name="gender" class="form-control" id="gender" required>
                                    <option>Male</option>
                                    <option>Female</option>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer d-flex justify-content-center">
                            <button class="btn btn-primary" type="submit">Confirm</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script src="js/jquery-3.7.0.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        let dob = document.getElementById("dob");
                
        function checkDOB(dob){
            let birthDate = new Date(dob.value);
            birthDate.setFullYear(birthDate.getFullYear() + 18);
//            if (birthDate > new Date()) {
//                dob.classList.add("is-invalid");
//            } else {
//                dob.classList.remove("is-invalid");
//            }
            return birthDate > new Date();
        }
        function checkPhone(phone){
            
            return birthDate > new Date();
        }
        (function () {
            'use strict';
            window.addEventListener('load', function () {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                let forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                let validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();

    </script>
</html>
