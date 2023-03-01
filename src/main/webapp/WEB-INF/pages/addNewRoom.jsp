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
        <form method="post" action="${pageContext.servletContext.contextPath}/user/addRoom">
            <h2 class="title">Room details:</h2>

            <tags:roomStringForm room="${room}" name="city" label="City:"
                                 possibleErrors="${possibleErrors}"></tags:roomStringForm>

            <tags:roomStringForm room="${room}" name="street" label="Street:"
                                 possibleErrors="${possibleErrors}"></tags:roomStringForm>

            <tags:roomStringForm room="${room}" name="houseNumber" label="House number:"
                                 possibleErrors="${possibleErrors}"></tags:roomStringForm>

            <tags:roomStringForm room="${room}" name="square" label="Square:"
                                 possibleErrors="${possibleErrors}"></tags:roomStringForm>

            <tags:roomStringForm room="${room}" name="numberOfRooms" label="Number of rooms:"
                                 possibleErrors="${possibleErrors}"></tags:roomStringForm>

            <button class="submit-button">Submit</button>
        </form>
    </div>
</tags:master>