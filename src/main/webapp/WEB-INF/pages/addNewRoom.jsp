<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Realtor management app">
    <head>
        <link rel="stylesheet" href="styles/user.css">
        <script src="js/sort.js"></script>
    </head>
    <form method="post" action="${pageContext.servletContext.contextPath}/user/addRoom">
        <h2>Room details:</h2>
        <table>
            <tr>
                <tags:roomStringForm room="${room}" name="city" label="City:"
                                     possibleErrors="${possibleErrors}"></tags:roomStringForm>
            </tr>
            <tr>
                <tags:roomStringForm room="${room}" name="street" label="Street:"
                                     possibleErrors="${possibleErrors}"></tags:roomStringForm>
            </tr>
            <tr>
                <tags:roomStringForm room="${room}" name="houseNumber" label="House number:"
                                     possibleErrors="${possibleErrors}"></tags:roomStringForm>
            </tr>
            <tr>
                <tags:roomStringForm room="${room}" name="square" label="Square:"
                                     possibleErrors="${possibleErrors}"></tags:roomStringForm>
            </tr>
            <tr>
                <tags:roomStringForm room="${room}" name="numberOfRooms" label="Number of rooms:"
                                     possibleErrors="${possibleErrors}"></tags:roomStringForm>
            </tr>
            </tr>
        </table>
        <p>
            <button>Submit</button>
        </p>
    </form>
</tags:master>