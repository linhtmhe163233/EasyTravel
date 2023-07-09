<%-- 
    Document   : LandingPage
    Created on : 17/05/2023
    Author     : DucTM
    Update on  : 16/06/2023, implement update button
    update on  : 18/06/2023, implement disable/enable
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
        <script src="js/jquery-3.7.0.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <title>Easy Travel | Tour detail</title>
        <style>
            .comment-box{

                padding:5px;
            }
            
            .comment-area textarea{
                resize: none;
                border: 1px solid #ad9f9f;
            }
            .form-control:focus {
                color: #495057;
                background-color: #fff;
                border-color: #ffffff;
                outline: 0;
                box-shadow: 0 0 0 1px rgb(255, 0, 0) !important;
            }

            .send {
                color: #fff;
                background-color: #ff0000;
                border-color: #ff0000;
            }

            .send:hover {
                color: #fff;
                background-color: #f50202;
                border-color: #f50202;
            }


            .rating {
                display: flex;
                margin-top: -10px;
                flex-direction: row-reverse;
                margin-left: -4px;
                float: left;
            }

            .rating>input {
                display: none
            }

            .rating>label {
                position: relative;
                width: 19px;
                font-size: 25px;
                color: #ff0000;
                cursor: pointer;
            }

            .rating>label::before {
                content: "\2605";
                position: absolute;
                opacity: 0
            }

            .rating>label:hover:before,
            .rating>label:hover~label:before {
                opacity: 1 !important
            }

            .rating>input:checked~label:before {
                opacity: 1
            }

            .rating:hover>input:checked~label:before {
                opacity: 0.4
            }

        </style>
    </head>
    <body>
        <c:import url="./Layout/Header.jsp"></c:import>
            <div class="rounded w-75 mx-auto" style="background: #DDD0C8;">
                <h1 class="text-center mt-2" style="color: #323232;">${tour.name}</h1>
            <img class="img-fluid img-thumbnail d-block mx-auto" 
                 src="./images/${tour.image}" alt="${tour.name}"
                 style="height: 50vh;">
            <div class="float-right mr-4">
                <c:if test="${sessionScope.user!=null && sessionScope.user.role=='Travel Agent' 
                              && sessionScope.user.id==tour.agentId}">
                    <% request.getSession().setAttribute("tour", request.getAttribute("tour")); %>
                    <form action="tour" method="post">
                        <a href="tours?act=update" type="button" class="btn btn-info">
                            Update
                        </a>
                        <input type="hidden" value="${tour.id}" name="id">
                        <c:if test="${tour.enabled}">
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#disableModal">
                                Disable
                            </button>
                            <div class="modal fade" id="disableModal" tabindex="-1" role="dialog" 
                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Are you sure?</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            Tourists can't access a disabled tour but you can undo this action anytime.
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-danger" name="disable">Disable tour</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${!tour.enabled}">
                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#enableModal">
                                Enable
                            </button>
                            <div class="modal fade" id="enableModal" tabindex="-1" role="dialog" 
                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Are you sure?</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            Tourists can access this tour again.
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-success" name="enable">Enable tour</button>
                                        </div>
                                    </div>
                                </div>
                            </div>     

                        </c:if>
                    </form>
                </c:if>
                <c:if test="${sessionScope.user!=null 
                              && sessionScope.user.role=='Tourist'
                              && tour.enabled}">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBookTour" id="book">
                        Book
                    </button>
                    <div class="modal fade" id="modalBookTour" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header text-center">
                                    <h4 class="modal-title w-100 font-weight-bold">Book this tour</h4>
                                    <button type="button" class="close btn btn-danger" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form action="book" method="POST" novalidate id="form" class="needs-validation">
                                    <input type="hidden" value="${tour.id}" name="tourId">
                                    <div class="modal-body mx-3">
                                        <div class="md-form mb-5">
                                            <label data-error="wrong" data-success="right" for="dob">Start date</label>
                                            <input type="date" id="startDate" class="form-control" name="startDate" 
                                                   required>
                                            <div class="valid-feedback">Looks good!</div>
                                            <div class="invalid-feedback">Your tour can only start in at least 3 days!</div>
                                        </div>
                                        <div class="md-form mb-5">
                                            <label data-error="wrong" data-success="right" for="phone">
                                                Number of tourists(*)
                                            </label>
                                            <input type="number" id="tourists_quantity" class="form-control validate" 
                                                   name="touristsQuantity" required max="${tour.maxQuantity}"
                                                   min="1" value="${tour.maxQuantity}">
                                            <div class="valid-feedback">Looks good!</div>
                                            <div class="invalid-feedback">
                                                Number of tourists should be between 1 and ${tour.maxQuantity}
                                            </div>
                                        </div>
                                        <div class="form-group mb-5">
                                            <label data-error="wrong" data-success="right" for="note">
                                                Note
                                            </label>
                                            <textarea class="form-control validate" id="note" rows="3" 
                                                      name="note" maxlength="300"></textarea>
                                            <div class="valid-feedback">Looks good!</div>
                                            <div class="invalid-feedback">Too long</div>
                                        </div> 
                                    </div>
                                    <div class="modal-footer d-flex justify-content-center">
                                        <button class="btn btn-primary" type="submit" name="book">Confirm</button>
                                        <button class="btn btn-info" type="button" name="pay" id="pay">Pay</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:if>

            </div>
            <div class="pb-4"></div>
        </div>
        <div class="rounded w-75 mx-auto" style="background: #DDD0C8;
             color: #323232;">
            <h2 class="ml-4 mt-2">Detail</h2>   
            <div class="ml-4 mt-2"><b>From </b> ${tour.agentName}</div>
            <div class="ml-4 mt-2"><b>Type:</b> ${tour.type}</div>
            <div class="ml-4 mt-2">
                <b>Price: </b><fmt:formatNumber type="number" maxFractionDigits = "3" 
                                  value="${tour.price}"></fmt:formatNumber> VND / ${tour.tripLength} days
                </div>
                <div class="ml-4 mt-2"><b>Available: </b>
                    from ${tour.availableFrom} to ${tour.availableTo}
            </div>
            <div class="ml-4 mt-2"><b>Max: </b>
                ${tour.maxQuantity} people
            </div>
            <div class="ml-4 mt-2">Vehicle, hotel, ... will be assigned after you book</div>
        </div>
        <div class="rounded w-75 mx-auto" style="background: #DDD0C8;
             color: #323232;">
            <h2 class="ml-4 mt-2">More description</h2>   
            <div class="ml-4 mt-2">${tour.description}</div>
        </div>
        <form action="feedback" method="post">
            <div class="text-center text-uppercase mt-4">
                <h1>Feedback</h1>
                <p style="color:red">${mess}</p>   

                <div>
                    <c:if test="${sessionScope.user!=null}">
                        <div class="comment-box ml-2 w-75 mx-auto">

                            <!--                <h4>Add a comment</h4>-->
                            <input type="hidden" name="tourID" value="${tour.id}">
                            <div class="rating"> 
                                <input type="radio" name="rating" value="5" id="5" required><label for="5">☆</label>
                                <input type="radio" name="rating" value="4" id="4" required><label for="4">☆</label> 
                                <input type="radio" name="rating" valque="3" id="3" required><label for="3">☆</label>
                                <input type="radio" name="rating" value="2" id="2" required><label for="2">☆</label>
                                <input type="radio" name="rating" value="1" id="1" required><label for="1">☆</label>
                            </div>

                            <div class="comment-area">
                                <textarea class="form-control" name ="content" placeholder="what is your view?" rows="4"></textarea>
                                 
                            </div>
                            <button class="btn btn-success float-left" type="submit">Send</button>  
                        </div>
                    </c:if>
                    </div>
                </div>
            </form>
            <br><!-- commented -->

            <div class="container mt-4">
                <c:forEach items="${listfb}" var="fb" varStatus="loop">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="media g-mb-30 media-comment">

                                <div class="media-body u-shadow-v18 g-bg-secondary g-pa-30"  style="border:1px  outset #999999">
                                    <div class="g-mb-15" >
                                        <h5 class="h5 g-color-gray-dark-v1 mb-0">${fb.fullName}</h5>
                                        <c:forEach begin="1" end="${fb.rating}">
                                            <span style="color: orangered;font-size: 25px;">☆</span>
                                        </c:forEach>
                                        <br>
    <!--                                    <span class="g-color-gray-dark-v4 g-font-size-10">${fb.time}</span>-->
                                        <small class="g-color-gray-dark-v4 g-font-size-10">${fb.time}</small>

                                    </div>

                                    <p Style="font-size:20px">${fb.content}</p>

                                    <ul class="list-inline d-sm-flex my-0">

                                        <li class="list-inline-item ml-auto">
                                            <a class="u-link-v5 g-color-gray-dark-v4 g-color-primary--hover" href="#!">
                                                <i class="fa fa-reply g-pos-rel g-top-1 g-mr-3"></i>
                                                Reply
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <br>

            <form action="tour" method="post" ${page.totalItems==0?'hidden':''}>
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

        </body>
        <script>
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
            let startDate = document.getElementById("startDate");

            Date.prototype.addYears = function (years) {
                let date = new Date(this);
                date.setYear(date.getFullYear() + years);
                return date;
            };

        Date.prototype.addDays = function (days) {
            let date = new Date(this);
            date.setDate(date.getDate() + days);
            return date;
        };
        startDate.min = new Date().addDays(3).toISOString().split("T")[0];
        startDate.valueAsDate = new Date().addDays(3);
        startDate.max = new Date('${tour.availableTo}').addDays(3);
        if(${book})
            $('#book').click();
    </script>
</html>
