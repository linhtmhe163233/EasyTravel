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
        <link rel="stylesheet" href="css/TourDetail.css">
        <title>Easy Travel | Tour detail</title>
    </head>
    <body>
        <c:import url="./Layout/Header.jsp"></c:import>
        <c:if test="${requestScope.toast!=null}">
            <c:import url="./Layout/Toast.jsp"></c:import>
        </c:if>
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
                        <a href="tours?act=update" class="btn btn-info">
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
                                      <input type="hidden" value="${tour.tripLength}" name="tripLength">
                                      <div class="modal-body mx-3" id="bookForm">
                                          <div class="md-form mb-3">
                                              <label data-error="wrong" data-success="right" for="dob">Start date</label>
                                              <input type="date" id="startDate" class="form-control" name="startDate" 
                                                     required>
                                              <div class="valid-feedback">Looks good!</div>
                                              <div class="invalid-feedback">Your tour can only start in at least 3 days!</div>
                                          </div>
                                          <div class="md-form mb-3">
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
                                          <div class="form-group mb-3">
                                              <label data-error="wrong" data-success="right" for="cost">
                                                  Total cost
                                              </label>
                                              <input type="number" class="form-control validate" id="cost"
                                                     name="cost" readonly>
                                              <div class="valid-feedback">Looks good!</div>
                                              <div class="invalid-feedback">Too long</div>
                                          </div> 
                                          <div class="form-group mb-3">
                                              <label data-error="wrong" data-success="right" for="note">
                                                  Note
                                              </label>
                                              <textarea class="form-control validate" id="note" rows="3" 
                                                        name="note" maxlength="300"></textarea>
                                              <div class="valid-feedback">Looks good!</div>
                                              <div class="invalid-feedback">Too long</div>
                                          </div>
                                          <div class="form-group mb-3">
                                              <label data-error="wrong" data-success="right" for="payment">
                                                  Payment
                                              </label>
                                              <select name="payment" id="payment" required class="form-control validate">
                                                  <option disabled selected hidden value>Select an option</option>
                                                  <option value="Cash">Cash</option>
                                                  <option value="Bank">Bank</option>
                                              </select>
                                              <div class="valid-feedback">Looks good!</div>
                                              <div class="invalid-feedback">Choose a payment method!</div>
                                          </div>
                                          <div id="onlinePay">
                                              Banking info
                                          </div>
                                      </div>
                                      <div class="modal-footer d-flex justify-content-center">
                                          <button class="btn btn-primary" type="submit" name="book">Confirm</button>
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
                <h1 id="fb">Feedback</h1>
                <p style="color:red">${mess}</p>   
                <div>
                    <c:if test="${checkFeedback}">
                        <div class="comment-box ml-2 w-75 mx-auto">
                            <input type="hidden" name="tourID" value="${tour.id}">
                            <div class="rating"> 
                                <input type="radio" name="rating" value="5" id="5" required><label for="5">☆</label>
                                <input type="radio" name="rating" value="4" id="4" required><label for="4">☆</label> 
                                <input type="radio" name="rating" value="3" id="3" required><label for="3">☆</label>
                                <input type="radio" name="rating" value="2" id="2" required><label for="2">☆</label>
                                <input type="radio" name="rating" value="1" id="1" required><label for="1">☆</label>
                            </div>
                            <div class="comment-area">
                                <textarea class="form-control" name ="content" placeholder="Give your feedback" rows="4" maxlength="800"></textarea>
                            </div>
                            <button class="btn btn-success float-left" type="submit">Send</button>  
                        </div>
                    </c:if>
                </div>
            </div>
        </form>
        <br>
        <div class="container justify-content-center mt-5 border-left border-right">
            <c:forEach items="${listfb}" var="fb" varStatus="loop">
                <div class="d-flex justify-content-center py-2">
                    <div class="second py-2 px-2"> 
                        <span class="text3  ">${fb.time}</span>
                        <div><p class="text4">${fb.fullName}</p></div>
                            <c:forEach begin="1" end="${fb.rating}">
                            <span  style="color: orangered;font-size: 25px;margin-left:10px;">☆</span>
                        </c:forEach>
                        <br>
                        <span class="text1">${fb.content}</span>
                        <div class="d-flex justify-content-between py-1 pt-2">
                            <div><span class="text1 text3o">Reply</span></div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <br>
        <nav class="mt-4" ${page.totalItems==0?'hidden':''}>
            <ul class="pagination justify-content-center">
                <li class="page-item">
                    <a class="page-link" href="tour?id=${tour.id}&index=${page.index-1}" ${page.index==1?"hidden":""}>
                        <
                    </a>
                </li>
                <li class="page-item ${page.index==1?"active":""}">
                    <a class="page-link" href="tour?id=${tour.id}&index=1">1</a>
                </li>
                <li class="page-item disabled" ${page.totalPage<5?"hidden":""}>
                    <span class="page-link">...</span>
                </li>
                <c:if test="${page.totalPage>2}">
                    <c:forEach var="p" begin="${page.pageStart}" end="${page.pageEnd}">
                        <li class="page-item ${page.index==p?"active":""}">
                            <a class="page-link" href="tour?id=${tour.id}&index=${p}">
                                ${p}
                            </a>
                        </li>
                    </c:forEach>
                </c:if>
                <li class="page-item disabled" ${page.totalPage<5?"hidden":""}>
                    <span class="page-link">...</span>
                </li>
                <li class="page-item ${page.index==page.totalPage?"active":""}" ${page.totalPage==1?"hidden":""}>
                    <a class="page-link" href="tour?id=${tour.id}&index=${page.totalPage}">
                        ${page.totalPage}
                    </a>
                </li>
                <li class="page-item" ${page.index==page.totalPage?"hidden":""}>
                    <a class="page-link" href="tour?id=${tour.id}&index=${page.index+1}">
                        >
                    </a>
                </li>
            </ul>
        </nav>
    </body>
    <script src="js/jquery-3.7.0.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
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
        $('.toast').toast('show');
        let startDate = $("#startDate")[0];
        if (startDate) {
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
            startDate.max = new Date('${tour.availableTo}').addDays(3).toISOString().split("T")[0];
        }
        if ('${book}'.length !== 0)
            $('#book').click();
        $('#onlinePay').hide();
        $('#payment').on("change", (e) => {
            if (e.target.value === 'Bank') {
                $('#onlinePay').show();
            } else {
                $('#onlinePay').hide();
            }
        });
        $('#cost').val(Number(${tour.price} *${tour.maxQuantity}).toFixed());
        $('#tourists_quantity').on("change", (e) => {
            $('#cost').val(${tour.price} * e.target.value);
        });
        if ('${scroll}'.length !== 0) {
            $('#fb')[0].scrollIntoView({
                behaviour: "smooth"
            });
        }
    </script>
</html>
