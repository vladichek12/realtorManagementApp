<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Realtor management app">
    <head>
        <link rel="stylesheet" href="styles/user.css">
        <script src="js/sort.js"></script>
    </head>
    <form method="post" action="${pageContext.servletContext.contextPath}/updateRoom/${neededRoom.getId()}">
        <h2>Room details:</h2>
        <table>
            <tr>
                <tags:roomUpdateStringForm room="${neededRoom}" name="city" label="City:"
                                           possibleErrors="${possibleErrors}"></tags:roomUpdateStringForm>
            </tr>
            <tr>
                <tags:roomUpdateStringForm room="${neededRoom}" name="street" label="Street:"
                                           possibleErrors="${possibleErrors}"></tags:roomUpdateStringForm>
            </tr>
            <tr>
                <tags:roomUpdateStringForm room="${neededRoom}" name="houseNumber" label="House number:"
                                           possibleErrors="${possibleErrors}"></tags:roomUpdateStringForm>
            </tr>
            <tr>
                <tags:roomUpdateNumbersForm room="${neededRoom}" name="square" label="Square:"
                                            possibleErrors="${possibleErrors}"></tags:roomUpdateNumbersForm>
            </tr>
            <tr>
                <tags:roomUpdateNumbersForm room="${neededRoom}" name="numberOfRooms" label="Number of rooms:"
                                            possibleErrors="${possibleErrors}"></tags:roomUpdateNumbersForm>
            </tr>
            </tr>
        </table>
        <p>
            <button>Submit</button>
        </p>
    </form>
</tags:master>