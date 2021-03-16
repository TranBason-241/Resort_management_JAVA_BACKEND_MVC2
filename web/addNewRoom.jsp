<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <c:if test="${sessionScope.USER.getRoleID() != 'AD'}">
        <c:url var="lF" value="MainController">
            <c:param name="btnAction" value="LoginFirst"></c:param>
        </c:url>
        <c:redirect url="${lF}"></c:redirect>
    </c:if>

    <meta charset="UTF-8">
    <title>Add room</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-6 m-auto">
                <br>

                <h5 class="text-center">ADD NEW ROOM</h5>

                <form action="MainController" method="POST">

                    <div class="form-group">
                        <label>RoomID</label>

                        <input type="text" name="txtRoomID" class="form-control" />
                        <h3 style="color: red">${requestScope.ERROR.getIdError()}</h3>
                    </div>
                    <div class="form-group">
                        <label>RoomName</label>
                        <input type="text" name="txtRoomName" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Price</label>
                        <input type="text" name="txtPrice" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label>Description</label>
                        <input type="text" name="txtDescription" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label for="imageFile">Select some images:</label>
                        <input type="file" name="link" id="imageFile" accept="image/*" multiple>
                    </div>
                    <input class="btn btn-primary" type="submit" name="btnAction" value="ADDNEWROOM"/>

                </form>
            </div>
                        <c:url var="back" value="MainController" >
                            <c:param name="btnAction" value="backtomanage" ></c:param>
                        </c:url>
            <a href="${back}">
                Back to Manager Page

            </a>
        </div>
    </div>
</body>
</html>