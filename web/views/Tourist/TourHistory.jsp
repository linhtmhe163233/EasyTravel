<%-- 
    Document   : LandingPage
    Created on : May 17, 2023, 8:05:10 PM
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
        <script src="js/jquery-3.7.0.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <title>Easy Travel | Tour History</title>
    </head>
    <body>
        <c:import url="../Layout/Header.jsp"></c:import>
            <div id="accordion" class="w-75 mx-auto mt-4">
                <div class="row card-header font-weight-bold">
                    <div class="col-1">
                        #
                    </div>
                    <div class="col-2">
                        Book time
                    </div>
                    <div class="col-3">
                        Tour name
                    </div>
                    <div class="col-2">
                        Start date
                    </div>
                    <div class="col-2">
                        Tourists quantity
                    </div>
                    <div class="col-2">
                        Status
                    </div>
                </div>
            <c:forEach items="${list}" var="booking" varStatus="loop">
                <c:set var="idx" value="${booking.id}"></c:set>
                    <div class="card">
                        <div class="card-header row click" id="heading${idx}" role="button">
                        <div class="col-1">
                            ${idx}
                        </div>
                        <div class="col-2">
                            ${booking.bookTime}
                        </div>
                        <div class="col-3">
                            ${booking.tourName}
                        </div>
                        <div class="col-2">
                            ${booking.startDate}
                        </div>
                        <div class="col-2">
                            ${booking.touristsQuantity}
                        </div>
                        <div class="col-2 status ${booking.status=='Unpaid'?'text-warning':
                                                   booking.status=='Paid'?'text-info':
                                                   booking.status=='Ready'?'text-primary':
                                                   booking.status=='Done'?'text-success':'text-danger'}">
                                 ${booking.status}
                             </div>
                        </div>
                        <div id="collapse${idx}" class="collapse" data-parent="#accordion" aria-labelledby="heading${idx}">
                            <div class="card-body row">
                                <div class="col-8">
                                    <b>Note: </b>${booking.note}
                                    <c:if test="${booking.payment=='Bank' && booking.status=='Unpaid'}">
                                        <br>
                                        <b>Online payment: </b>${booking.bank} - ${booking.code}
                                        <div class="card-body d-flex justify-content-center">
                                            <img src="images/Payment/${booking.qr}" alt="QR" class="img-fluid"
                                                 style="max-height: 30rem; max-width: 30rem"/>
                                        </div>
                                    </c:if>
                                    <c:if test="${booking.reason!=null}">
                                        <br>
                                        <b>Reason for cancel:  </b>${booking.reason}
                                    </c:if>
                                    <div id="facility${idx}"></div>    
                                </div>
                                <div class="col-4 d-flex align-items-end justify-content-end">
                                    <c:if test="${booking.status=='Unpaid'
                                                  ||booking.status=='Paid'
                                                  ||booking.status=='Ready'}">
                                          <button class="btn btn-danger" data-toggle="modal" 
                                                  data-target="#modalDecline${booking.id}">
                                              Cancel tour
                                          </button>  
                                    </c:if>
                                    <a href="tour?id=${booking.tourId}" class="btn btn-info">Go to tour</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${booking.status=='Unpaid'
                                  ||booking.status=='Paid'
                                  ||booking.status=='Ready'}">
                          <div class="modal fade" id="modalDecline${booking.id}" tabindex="-1" role="dialog">
                              <div class="modal-dialog" role="document">
                                  <div class="modal-content">
                                      <div class="modal-header text-center">
                                          <h4 class="modal-title w-100 font-weight-bold">Decline this request</h4>
                                          <button type="button" class="close btn btn-danger" data-dismiss="modal" 
                                                  aria-label="Close">
                                              <span aria-hidden="true">&times;</span>
                                          </button>
                                      </div>
                                      <form action="handlebooking" method="POST" novalidate id="declineform${booking.id}" 
                                            class="needs-validation">
                                          <input type="hidden" name="id" value="${booking.id}">
                                          <input type="hidden" min="1" name="index" value="${page.index}"> 
                                          <div class="modal-body mx-3">
                                              <div class="md-form mb-2">
                                                  <label data-error="wrong" data-success="right" for="reason">
                                                      Add reason(*)
                                                  </label>
                                                  <textarea class="form-control validate" 
                                                            id="reason" rows="3" name="reason" 
                                                            maxlength="300" required></textarea>
                                                  <div class="valid-feedback">Looks good!</div>
                                                  <div class="invalid-feedback">
                                                      Add some reason about your decline
                                                  </div>
                                              </div>
                                          </div>
                                          <div class="modal-footer d-flex justify-content-center">
                                              <button class="btn btn-primary" type="submit" name="cancel">
                                                  Confirm
                                              </button>
                                          </div>
                                      </form>
                                  </div>
                              </div>
                          </div>
                    </c:if>
                </c:forEach>
            </div>        
            <form action="history" method="POST" ${page.totalItems==0?'hidden':''}>
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
            <c:import url="../Layout/Footer.jsp"></c:import>
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
            $('.click').click((e) => {
                let id = e.currentTarget.id.substr(7);
                $('#collapse' + id).collapse('toggle');
                let status = $('#heading' + id).children('.status').text().trim();
                if (status === 'Ready' || status === 'Done') {
                    $('#facility' + id).load("handlebooking", {facility: true, id: id});
                }
            });
        </script>
    </html>
