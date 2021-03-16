<%-- 
    Document   : management
    Created on : Nov 5, 2020, 1:06:39 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <c:if test="${sessionScope.USER.getRoleID() != 'AD'}">
            <c:url var="lF" value="MainController">
                <c:param name="btnAction" value="LoginFirst"></c:param>
            </c:url>
            <c:redirect url="${lF}"></c:redirect>
        </c:if>
    <head>
        <title>Management Room Page</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>

    <body>

        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="ManageController">SONBA LUXURY RESORT</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                    <form class="form-inline my-2 my-lg-0">
                        <span style="Color: aliceblue;padding-right: 200px; "> Hello ${sessionScope.USER.getUserName()}   </span>
                        <c:url var="logout" value="MainController" >
                            <c:param name="btnAction" value="logout" ></c:param>
                        </c:url>
                        <a class="btn btn-success btn-sm ml-3" href="${logout}">
                            LOGOUT
                        </a>
                    </form>
                </div>
            </div>
        </nav>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">MANAGE ROOM</h1>
            </div>
        </section>
        <div style="padding-left: 1150px">

            <c:url var="addnew" value="MainController" >
                <c:param name="btnAction" value="addnew"  ></c:param>
            </c:url>
            <a style="margin-left: 300px" class="btn btn-success btn-sm ml-3" href="${addnew}">
                ADD NEW ROOM
            </a>

        </div>

        <div class="container mb-4">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">NO </th>
                                    <th scope="col">ROOM ID</th>
                                    <th scope="col" class="text-center">NAME</th>
                                    <th scope="col" class="text-center">Description</th>
                                    <th scope="col" class="text-center">Price</th>
                                    <th scope="col" class="text-center">Status</th>


                                    <th> </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="list" value="${requestScope.LIST}"></c:set>
                                <c:if test="${list!=null}" >
                                    <c:forEach var="room" items="${list}" varStatus="counter" >

                                    <form action="MainController" method="POST">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${room.getRoomID()} <input name="txtID" type="hidden" value="${room.getRoomID()}" > </td>
                                            <td><input name="txtName" class="form-control" type="text" value="${room.getRoomName()}" /></td>
                                            <td><input name ="txtDescription" class="form-control" type="text" value="${room.getDescription()}" /></td>
                                            <td> <input name="txtPrice" class="form-control" type="text" value="${room.getPrice()}" /></td>
                                            <td>${room.isStatus()} </td>
                                            <td><input name="txtRoomID" type="hidden" value="${room.getRoomID()}" ></td>
                                            <td class="text-right"><button type="submit" name="btnAction" value="managerDelete" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                                            <td class="text-right"><button type="submit" name="btnAction" value="managerUpdate" class="btn btn-sm btn-warning"><i class="fa fa-edit"></i> </button></td>
                                        </tr>
                                    </form>


                                </c:forEach>

                            </c:if>



                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>

        <!-- Footer -->


        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
