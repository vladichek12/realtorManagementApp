<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Realtor management app">
    <head>
        <title>Library</title>
        <link rel="stylesheet" href="styles/user.css">
        <script src="js/sort.js"></script>
    </head>
    <p>
    <div class="wrap">
        <div>Rooms</div>
        <table>
            <tr>
                <td>City</td>
                <td>Street</td>
                <td>House number</td>
                <td>Number of rooms</td>
                <td>Square</td>
                <c:if test="${not empty currentUserRooms}">
                    <td/>
                </c:if>
            </tr>
            <tbody>
            <c:forEach var="room" items="${rooms}">
                <tr>
                    <td>${room.getAddress().getCity()}</td>
                    <td>${room.getAddress().getStreet()}</td>
                    <td>${room.getAddress().getHouseNumber()}</td>
                    <td>${room.getNumberOfRooms()}</td>
                    <td>${room.getSquare()}</td>
                    <c:if test="${not empty currentUserRooms}">
                        <c:forEach var="_room" items="${currentUserRooms}">
                            <c:if test="${_room.getId().equals(room.getId())}">
                                <td>
                                    <button form="deleteForm"
                                            formaction="${pageContext.servletContext.contextPath}/deleteRoom/${room.getId()}">
                                        Delete
                                    </button>
                                    <br/>
                                    <br/>
                                    <button form="updateForm"
                                            formaction="${pageContext.servletContext.contextPath}/updateRoom/${room.getId()}">
                                        Update
                                    </button>
                                </td>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </p>
    <br>
    <p>
    <form method="get" action="${pageContext.servletContext.contextPath}/user/addRoom">
        <button>
            Add new room
        </button>
    </form>
    <form id="deleteForm" method="post"></form>
    <form id="updateForm" method="get"></form>
    </p>
</tags:master>