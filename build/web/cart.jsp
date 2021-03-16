
<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dto.RoomDTO"%>
<%@page import="dto.CartDTO"%>
<%@page import="java.util.concurrent.TimeUnit"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>



    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>  <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

    </head>
    <body>

       

        <c:set var="cart" value="${sessionScope.CART}" ></c:set>

            <div class="row">
                <div class="col-sm-12 col-md-10 col-md-offset-1">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Product</th>
                                <th style="text-align: center;">Date</th>
                                <th style="text-align: center;">Numer of Date</th>
                                <th class="text-center">Price</th>
                                <th class="text-center">Total</th>
                                <th> </th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:if test="${not empty cart}">
                            <c:set var="total"  value="0" ></c:set>
                            <c:forEach var="elem" items="${cart.getCart().values()}" >

                                <c:set var="total" value="${elem.getPrice()*elem.getDateNumber()}" ></c:set>
                                    <tr>
                                        <td class="col-sm-8 col-md-5">
                                            <div class="media">
                                                <a class="thumbnail pull-left" href="#"> <img class="media-object" src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png" style="width: 72px; height: 72px;"> </a>
                                                <div class="media-body">
                                                    <h4 class="media-heading"><a href="#">${elem.getRoomName()}</a></h4>
                                                <h5 class="media-heading">  <a href="#">sale 0%</a></h5>
                                                <span>Status: </span><span class="text-success"><strong>No Voucher</strong></span>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="col-sm-1 col-md-4" style="text-align: center">
            
                                        <h5> Days</h5>
                                    </td>
                                    <td class="col-sm-1 col-md-4" style="text-align: center">
            
                                        <h5> NumDays</h5>
                                    </td>
                                    <td class="col-sm-1 col-md-1 text-center"><strong>${elem.getPrice()}$</strong></td>

                                    <td class="col-sm-1 col-md-1 text-center"><strong>${elem.getPrice()*elem.getDateNumber()}$</strong></td>


                                    <td class="col-sm-1 col-md-1 text-center"><strong></strong></td>
                                    <td class="col-sm-1 col-md-1">

                                        <c:url var="delete" value="MainController">
                                            <c:param name="roomID" value="${elem.getRoomID()}"></c:param>
                                            <c:param name = "btnAction" value="delete"></c:param>
                                        </c:url>

                                        <a href="${delete}">
                                            <button type="button" class="btn btn-danger">
                                                <span class="glyphicon glyphicon-remove"></span> Remove
                                            </button></td>
                                    </a>

                                </tr>
                            </c:forEach>
                        </c:if>






                        <tr>
                            <td>   </td>
                            <td>   </td>
                            <td>   </td>
                            <td>
                                <h5>VAT</h5>
                            </td>
                            <td class="text-right">
                                <c:set var="vat" value="${(total/100)*10}" ></c:set>
                                <h5><strong>${vat}$</strong></h5>
                            </td>
                        </tr>
                        <tr>
                            <td>   </td>
                            <td>   </td>
                            <td>   </td>
                            <td>
                                <h3>Total</h3>
                            </td>
                            <td class="text-right">
                                <h3><strong>${total+vat}$</strong></h3>

                            </td>
                        </tr>
                        <tr>
                            <td>   </td>
                            <td>   </td>
                            <td>   </td>
                            <td>
                                <c:url var="Back" value="MainController">
                                    <c:param name="btnAction" value="back" ></c:param>
                                </c:url>
                                <a href="${Back}">
                                    <button type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                                    </button></td>
                            </a>                            
                            <td>


                                <c:url var="checkOut" value="MainController">
                                    <c:param name="btnAction" value="checkOut" ></c:param>
                                </c:url>
                                <a href="${checkOut}">
                                    <button type="button" class="btn btn-success">
                                        <span class="glyphicon glyphicon-play"> Checkout</span>
                                    </button>
                                    <h1 style="color: green">${requestScope.SUCCESS} </h1>
                            </td>

                            </a>                         

                        </tr>
                    </tbody>
                </table>
            </div>
        </div>


    </body>
</html>
