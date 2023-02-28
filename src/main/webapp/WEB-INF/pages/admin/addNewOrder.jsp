<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Realtor management app">
    <head>
        <link rel="stylesheet" href="styles/admin.css">
        <script src="js/sort.js"></script>
    </head>
    <div class="form">
        <h1 class="title">
            Create new order
        </h1>
        <form method="post" action="adminAddOrder">

            <p>Realtor</p>
            <select id = "realtors"  name = "realtors" required>
                <c:forEach var ="element" items = "${realtors}">
                    <option  value = ${element.getId()} >${element.getName()}</option>
                </c:forEach>
            </select>
            <br>

            <p>Room</p>
            <select id = "rooms"   name = "rooms" required>
                <c:forEach var ="element" items = "${rooms}">
                    <option  value = ${element.getId()} >${element.getAddress().toString()}</option>
                </c:forEach>
            </select>
            <br>

            <input class="submit-button" type = "submit" value = "Submit" onclick="multipleChoiseLimiter()"/>
        </form>
    </div>

</tags:master>
