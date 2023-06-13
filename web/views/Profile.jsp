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
        <title>EasyTravel</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <style>
            body {
                margin: 0;
                padding-top: 40px;
                color: #2e323c;
                background: #f5f6fa;
                position: relative;
                height: 100%;
            }
            .account-settings .user-profile {
                margin: 0 0 1rem 0;
                padding-bottom: 1rem;
                text-align: center;
            }
            .account-settings .user-profile .user-avatar {
                margin: 0 0 1rem 0;
            }
            .account-settings .user-profile .user-avatar img {
                width: 90px;
                height: 90px;
                -webkit-border-radius: 100px;
                -moz-border-radius: 100px;
                border-radius: 100px;
            }
            .account-settings .user-profile h5.user-name {
                margin: 0 0 0.5rem 0;
            }
            .account-settings .user-profile h6.user-email {
                margin: 0;
                font-size: 0.8rem;
                font-weight: 400;
                color: #9fa8b9;
            }
            .account-settings .about {
                margin: 2rem 0 0 0;
                text-align: center;
            }
            .account-settings .about h5 {
                margin: 0 0 15px 0;
                color: #007ae1;
            }
            .account-settings .about p {
                font-size: 0.825rem;
            }
            .form-control {
                border: 1px solid #cfd1d8;
                -webkit-border-radius: 2px;
                -moz-border-radius: 2px;
                border-radius: 2px;
                font-size: .825rem;
                background: #ffffff;
                color: #2e323c;
            }

            .card {
                background: #ffffff;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                border: 0;
                margin-bottom: 1rem;
            }
            a{
                color: blue
            }
        </style>

        <!--        <style>
                    body{
                        margin-top:20px;
                        color: #9b9ca1;
                    }
                    .bg-secondary-soft {
                        background-color: rgba(208, 212, 217, 0.1) !important;
                    }
                    .rounded {
                        border-radius: 5px !important;
                    }
                    .py-5 {
                        padding-top: 3rem !important;
                        padding-bottom: 3rem !important;
                    }
                    .px-4 {
                        padding-right: 1.5rem !important;
                        padding-left: 1.5rem !important;
                    }
                    .file-upload .square {
                        height: 250px;
                        width: 250px;
                        margin: auto;
                        vertical-align: middle;
                        border: 1px solid #e5dfe4;
                        background-color: #fff;
                        border-radius: 5px;
                    }
                    .text-secondary {
                        --bs-text-opacity: 1;
                        color: rgba(208, 212, 217, 0.5) !important;
                    }
                    .btn-success-soft {
                        color: #28a745;
                        background-color: rgba(40, 167, 69, 0.1);
                    }
                    .btn-danger-soft {
                        color: #dc3545;
                        background-color: rgba(220, 53, 69, 0.1);
                    }
                    .form-control {
                        display: block;
                        width: 100%;
                        padding: 0.5rem 1rem;
                        font-size: 0.9375rem;
                        font-weight: 400;
                        line-height: 1.6;
                        color: #29292e;
                        background-color: #fff;
                        background-clip: padding-box;
                        border: 1px solid #e5dfe4;
                        -webkit-appearance: none;
                        -moz-appearance: none;
                        appearance: none;
                        border-radius: 5px;
                        -webkit-transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
                        transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
                        transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
                        transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
                    }
                </style>-->
    </head>
    <body>
        <h1>My profile</h1>

        <div class="container">
            <div class="row gutters">

                <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <!--                                    <h6 class="mb-2 text-primary">Personal Details</h6>-->
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="fullName">Full Name</label>
                                        <input type="text" class="form-control" id="fullName" placeholder="Enter full name" value="${user.fullname}">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="eMail">Email</label>
                                        <input type="email" class="form-control" id="eMail" placeholder="Enter email ID" value="${user.email}">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="phone">Phone</label>
                                        <input type="text" class="form-control" id="phone" placeholder="Enter phone number" value="${user.phone}">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="website">Date of birthday</label>
                                        <input type="date" class="form-control" id="password" placeholder="" value="${user.dob}">
                                    </div>
                                </div>
                            </div>
                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <!--                                    <h6 class="mt-3 mb-2 text-primary">Address</h6>-->
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <!--                                        <label for="Street">Password</label>-->
                                        <a href="/EasyTravel/home">Change password</a>

                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="ciTy">Role: ${user.role}</label>
                                        <input type="text" class="form-control" id="ciTy" placeholder="Enter City" value="${user.role}">
                                    </div>
                                </div>

                            </div>
                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <div class="text-right">
                                        <a href="/EasyTravel/home"class="btn btn-secondary">Cancel</a>
                                        <button type="button" id="submit" name="submit" class="btn btn-primary">Update</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css" integrity="sha256-2XFplPlrFClt0bIdPgpz8H7ojnk10H69xRqd9+uTShA=" crossorigin="anonymous" />
        
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                             Page title 
                            <div class="my-5">
                                <h3>My Profile</h3>
                                <hr>
                            </div>
                             Form START 
                            <form class="file-upload">
                                <div class="row mb-5 gx-5">
                                     Contact detail 
                                    <div class="col-xxl-8 mb-5 mb-xxl-0">
                                        <div class="bg-secondary-soft px-4 py-5 rounded">
                                            <div class="row g-3">
                                                <h4 class="mb-4 mt-0">Contact detail</h4>
                                                 First Name 
                                                <div class="col-md-6">
                                                    <label class="form-label">Full name:</label>
                                                    <input type="text" 
                                                           name="fullname"
                                                           class="form-control" 
                                                           placeholder="" 
                                                           aria-label="Full name" 
                                                           value="${sessionScope.user.fullname}">
                                                </div>
                                                 Last name 
                                                <div class="col-md-6">
                                                    <label class="form-label">Last Name *</label>
                                                    <input type="text"
                                                           class="form-control" 
                                                           placeholder="" 
                                                           -label="Last name" 
                                                           value="Doe">
                                                </div>
                                                 Phone number 
                                                <div class="col-md-6">
                                                    <label class="form-label">Phone number *</label>
                                                    <input type="text" 
                                                           class="form-control" 
                                                           placeholder="" 
                                                           aria-label="Phone number" 
                                                           value="(333) 000 555">
                                                </div>
                                                 Mobile number 
                                                <div class="col-md-6">
                                                    <label class="form-label">Date of birthday:</label>
                                                    <input type="date" class="form-control" placeholder="" aria-label="Phone number" value="${sessionScope.user.dob}">
                                                </div>
                                                 Email 
                                                <div class="col-md-6">
                                                    <label for="inputEmail4" class="form-label">Email:</label>
                                                    <input type="email" class="form-control" id="inputEmail4" value="${sessionScope.user.email}">
                                                </div>
                                                 Skype 
                                                <div class="col-md-6">
                                                    <label class="form-label">Skype *</label>
                                                    <input type="text" class="form-control" placeholder="" aria-label="Phone number" value="Scaralet D">
                                                </div>
                                            </div>  Row END 
                                        </div>
                                    </div>-->
        <!--                             Upload profile 
                                    <div class="col-xxl-4">
                                        <div class="bg-secondary-soft px-4 py-5 rounded">
                                            <div class="row g-3">
                                                <h4 class="mb-4 mt-0">Upload your profile photo</h4>
                                                <div class="text-center">
                                                     Image upload 
                                                    <div class="square position-relative display-2 mb-3">
                                                        <i class="fas fa-fw fa-user position-absolute top-50 start-50 translate-middle text-secondary"></i>
                                                    </div>
                                                     Button 
                                                    <input type="file" id="customFile" name="file" hidden="">
                                                    <label class="btn btn-success-soft btn-block" for="customFile">Upload</label>
                                                    <button type="button" class="btn btn-danger-soft">Remove</button>
                                                     Content 
                                                    <p class="text-muted mt-3 mb-0"><span class="me-1">Note:</span>Minimum size 300px x 300px</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>  Row END 
        
        
        
        -->
        <!--                        <div class="gap-3 d-md-flex justify-content-md-end text-center">
                                    <button type="button" class="btn btn-danger btn-lg">Delete profile</button>
                                    <button type="button" class="btn btn-primary btn-lg">Update profile</button>
                                </div>
                            </form>  Form END 
                        </div>
                    </div>
                </div>
        -->    </body>
</html>
