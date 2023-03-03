<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Realtor management app">

    <head>
        <link rel="stylesheet" href="styles/user.css">
        <script src="js/sort.js"></script>
        <style>
            <%@include file="/styles/admin.css"%>
        </style>
    </head>
    <div class="form">
        <form method="post" action="${pageContext.servletContext.contextPath}/updateRoom/${room.id}">
            <h2 class="title">Room details:</h2>
            <input type="hidden" value="${room.id}" name="id"/>
            <tags:roomStringFormUpdateWithAddress address="${room.address}" name="city" label="City:"
                                                  possibleErrors="${possibleErrors}"></tags:roomStringFormUpdateWithAddress>

            <tags:roomStringFormUpdateWithAddress address="${room.address}" name="street" label="Street:"
                                                  possibleErrors="${possibleErrors}"></tags:roomStringFormUpdateWithAddress>

            <tags:roomStringFormUpdateWithAddress address="${room.address}" name="houseNumber" label="House number:"
                                                  possibleErrors="${possibleErrors}"></tags:roomStringFormUpdateWithAddress>

            <tags:roomStringFormUpdate room="${room}" name="square" label="Square:"
                                       possibleErrors="${possibleErrors}"></tags:roomStringFormUpdate>

            <tags:roomStringFormUpdate room="${room}" name="numberOfRooms" label="Number of rooms:"
                                       possibleErrors="${possibleErrors}"></tags:roomStringFormUpdate>

            <button class="submit-button">Submit</button>
        </form>
    </div>
</tags:master>