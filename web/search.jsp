<%-- 
    Document   : search
    Created on : Nov 5, 2020, 12:22:10 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <c:if test="${sessionScope.USER.getRoleID() != 'US'}">
            <c:url var="lF" value="MainController">
                <c:param name="btnAction" value="LoginFirst"></c:param>
            </c:url>
            <c:redirect url="${lF}"></c:redirect>
    </c:if>
    
    <head>
        <title>Search</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="author" content="colorlib.com">
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500" rel="stylesheet" />
        <link href="css/search.css" rel="stylesheet" />
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>

    <body>

        <div class="header">

            <ul style="margin-bottom: 5px;margin-right: 0">
                <li><a style="font-size: 30px" href="#">HOME</a></li>
                <li>
                    <a href="#"></a>
                </li>
                <li><a style="font-size: 20px" href="#sale">HOT DEAL</a></li>
                    <c:url var="view" value="MainController">
                        <c:param name="btnAction" value="view"></c:param>
                    </c:url>
                <li><a style="font-size:20px " href="${view}">VIEW CART</a></li>
                <li>
                    <c:url var="logout" value="MainController" >
                        <c:param name="btnAction" value="logout" ></c:param>
                    </c:url>
                    <a style="font-size:20px " href="${logout}">LOGOUT</a>
                </li>
                <li style="margin-left: 400px;">
                    <p style="display: inline; color: white;font-size: 20px;width: 100%">WELCOME ${sessionScope.USER.getUserName()}</p>
                </li>
            </ul>

        </div>
        <div class="s002">
            <form action="MainController" method="POST">
                <fieldset>
                    <legend>SONBA LUXURY RESORT</legend>
                    <h3 style="padding-left: 700px;text-align: center;color: white" >${requestScope.ERROR_DATE}</h3>
                </fieldset>
                <div class="inner-form">
                    <div class="input-field first-wrap">
                        <!-- <div class="icon-wrap">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                    <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"></path>
                  </svg>
                        </div> -->
                        <input style="color: red;font-size: 20px" id="search" type="text" value="Must chosse date before search"  disabled="disabled" />
                        
                    </div>
                    <div class="input-field second-wrap">
                        <div class="icon-wrap">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                            <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
                            </svg>
                        </div>
                        <input class="datepicker"  id="depart" type="text" 
                               name="depart" value="${sessionScope.depart}"/>
                    </div>
                    <div class="input-field third-wrap">
                        <div class="icon-wrap">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                            <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
                            </svg>
                        </div>
                        <input class="datepicker"  id="return" type="text" 
                               name="returnn" value="${sessionScope.returnn}"/>
                    </div>
                    <div class="input-field fouth-wrap">
                        <div class="icon-wrap">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                            <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"></path>
                            </svg>
                        </div>
                        <select data-trigger="" name="choices-single-defaul">
                            <option placeholder="">2 Adults</option>
                            <option>3 Adults</option>
                            <option>4 Adults</option>
                            <option>5 Adults</option>
                        </select>
                    </div>
                    <div class="input-field fifth-wrap">
                        <button class="btn-search" name="btnAction" value="search" type="submit">SEARCH</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="services"></div>
    <div class="footer"></div>

    <c:set var="list" value="${sessionScope.LIST}"></c:set>
    <c:if test="${list!=null}">
        <h1 style="text-align: center;padding-top: 22px"> Avaliable Room </h1>
        <div style="background-color: black;">
            <div id="showRoom" class="container" style="background-color: black">            
                <div class="row">
                    <c:forEach var="room" items="${list}">
                        <form class="card col-4" style="width: 18rem;">
                            <img src="${room.getImage()}" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">${room.getRoomName()}</h5>
                                <h5 style="color: red" class="card-title">${room.getPrice()}$</h5>
                                <p class="card-text">${room.getDescription()}</p>
                                <c:url var="add" value="MainController" >
                                    <c:param name="roomID" value="${room.getRoomID()}" ></c:param>
                                    <c:param name="btnAction" value="add" ></c:param>
                                </c:url>
                                <a href="${add}" class="btn btn-primary">Add</a>
                            </div>                                     
                        </form>  
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>



    <div class="banner" id="sale">
        <div class="banner__circle">
            <p>SALE OF</p>
            <P>50%</P>
            <P>Ervery room</P>
            <p>5/3 to 7/3</p>

        </div>
        <h1 id="corona">Affer <span style="color: red;font-size: 50px;">Corona</span> supper <span style="color: red;font-size: 70px;">SALE</span></h1>
    </div>

    <script src="js/extention/choices.js"></script>
    <script src="js/extention/flatpickr.js"></script>
    <script>
        flatpickr(".datepicker", {});
    </script>
    <script>
        const choices = new Choices('[data-trigger]', {
            searchEnabled: false,
            itemSelectText: '',
        });
    </script>





    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>
