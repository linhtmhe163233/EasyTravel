<%-- 
    Document   : LandingPage
    Created on : May 17, 2023, 8:05:10 PM
    Author     : tranm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Easy Travel</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery-3.7.0.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="css/Profile.css">
    </head>
    <body style="min-width: 1100px;">
        <c:import url="./Layout/Header.jsp"></c:import>
            <h1 class="text-center">My profile</h1>
            <form action ="profile" method="post">
                <div class="mx-auto w-50">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="fullname">Full Name</label>
                                        <input type="text" class="form-control"name="fullname" id="fullname" placeholder="Enter full name" value="${user.fullname}"
                                           required pattern="^\s*\p{L}+(\s\p{L}+)*\s*$"
                                           maxlength="80">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" name="email" id="email" 
                                           placeholder="example@gmail.com" required maxlength=80 value="${user.email}">
                                    <p style="color:red">${message1}</p>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="phone">Phone</label>
                                    <input type="text" class="form-control" name="phone" id="phone" placeholder="Enter phone number" value="${user.phone}"
                                           required pattern="^0[0-9]{9}$">
                                    <p style="color:red">${message}</p>   
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="Date">Date of birthday</label>
                                    <input type="date" class="form-control" name="dob" id="dob" placeholder="Your birthday" value="${user.dob}" required >
                                </div>
                            </div>
                        </div>
                        <div class="row gutters">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12"><!--                                    <h6 class="mt-3 mb-2 text-primary">Address</h6>-->
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group"><a href="/EasyTravel/changepassword">Change password</a>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="role">Role: ${user.role}</label>
                                </div>
                            </div>
                        </div>
                        <div class="row gutters">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-right">
                                    <a href="/EasyTravel/home"class="btn btn-secondary">Cancel</a>
                                    <button type="submit" id="submit" name="submit" class="btn btn-primary">Update</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <c:if test="${sessionScope.user.role=='Travel Agent'}">
            <div class="container mt-4 w-50 mx-auto">
                <c:if test="${list!=null}">
                    <div id="accordion" class="mt-4">
                        <div class="row card-header font-weight-bold">
                            <div class="col-3">
                                Bank
                            </div>
                            <div class="col-9">
                                Code
                            </div>
                        </div>
                        <c:forEach items="${list}" var="payment">
                            <div class="card">
                                <div class="card-header row" id="heading${payment.id}" role="button" 
                                     onclick="this.children[0].click()">
                                    <button class="btn collapsed" data-toggle="collapse" data-target="#collapse${payment.id}" 
                                            aria-expanded="false" aria-controls="collapse${payment.id}" type="button" hidden="">
                                    </button>
                                    <div class="col-3">
                                        ${payment.bank}
                                    </div>
                                    <div class="col-9">
                                        ${payment.code}
                                    </div>
                                </div>
                                <div id="collapse${payment.id}" class="collapse" data-parent="#accordion" aria-labelledby="heading${payment.id}">
                                    <div class="card-body d-flex justify-content-center">
                                        <img src="images/Payment/${payment.qr}" alt="QR" class="img-fluid"
                                             style="max-height: 30rem; max-width: 30rem"/>
                                    </div>
                                    <!--                                    <button type="button" class="btn btn-danger float-right mr-2 mb-2" data-toggle="modal" 
                                                                                data-target="#deletePayment${payment.id}">
                                                                            Delete
                                                                        </button>
                                                                        <div class="modal fade" id="deletePayment${payment.id}" tabindex="-1" role="dialog" 
                                                                             aria-hidden="true">
                                                                            <div class="modal-dialog" role="document">
                                                                                <div class="modal-content">
                                                                                    <div class="modal-header">
                                                                                        <h5 class="modal-title" id="exampleModalLabel">Are you sure?</h5>
                                                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                            <span aria-hidden="true">&times;</span>
                                                                                        </button>
                                                                                    </div>
                                                                                    <div class="modal-body">
                                                                                        You should immediately add another payment method after this action!
                                                                                    </div>
                                                                                    <div class="modal-footer">
                                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                                                            Close
                                                                                        </button>
                                                                                        <form action="payment" method="post">
                                                                                            <input type="hidden" name="id" value="${payment.id}">
                                                                                            <button type="submit" class="btn btn-danger" name="delete">
                                                                                                Delete
                                                                                            </button>
                                                                                        </form>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>-->
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <button class="btn btn-info mb-4" type="button" id="addPayment">Add payment info</button>
                <div id="paymentForm">
                    <form method="post" action="payment" enctype="multipart/form-data" novalidate class="needs-validation">
                        <div class="form-row">
                            <div class="form-group col-3">
                                <label for="bank" data-error="wrong" data-success="right">Bank<span class="text-danger">*</span></label>
                                <input type="text" id="bank" name="bank" class="form-control validate" required>
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Enter a valid bank!</div>
                            </div>
                            <div class="form-group col-9">
                                <label for="bank" data-error="wrong" data-success="right">Code<span class="text-danger">*</span></label>
                                <input type="text" id="code" name="code" class="form-control validate" required 
                                       pattern="^[0-9]{6,40}$">
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Enter a valid code!</div>
                            </div>  
                        </div>
                        <div class="form-group">
                            <p>QR</p>
                            <div class="custom-file">
                                <input type="file" class="custom-file-input validate" id="qr" name="qr"  
                                       accept="image/*">
                                <label data-error="wrong" data-success="right" class="custom-file-label" for="qr">
                                    Choose image
                                </label>
                                <div class="valid-feedback">Looks good!</div>
                                <div class="invalid-feedback">Update image!</div>
                            </div>
                            <img id="qrDisplay" class="img-fluid mt-2">
                        </div>
                        <div class="text-right">
                            <button class="btn btn-danger" type="button" id="cancel">Cancel</button>
                            <button class="btn btn-primary" type="submit">Confirm</button>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
        <c:import url="./Layout/Footer.jsp"></c:import>
    </body>
    <script src="js/Profile.js" type="text/javascript"></script>
</html>
