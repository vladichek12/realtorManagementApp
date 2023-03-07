<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-16BE" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Realtor management app" word="Недвижимость">
    <html>
    <head>
        <title>Realtor agency</title>
        <link rel="stylesheet" href="styles/admin.css">
        <script src="js/sort.js"></script>
        <style>
            <%@include file="/styles/admin.css"%>
        </style>
    </head>
    <body>
    <c:set var="userRole" value="${sessionScope.currentUser.getUserRole().toString()}"/>
    <ul>
        <li><a href="${pageContext.servletContext.contextPath}/user">Помещения</a></li>
        <c:if test="${ userRole == 'ROLE_REALTOR'}">
            <li><a href="${pageContext.servletContext.contextPath}/orders" class="active">Мои заказы</a></li>
        </c:if>
        <c:if test="${ userRole != 'ROLE_REALTOR'}">
            <li><a href="${pageContext.servletContext.contextPath}/orders" class="active">Мои помещения</a></li>
        </c:if>
    </ul>
    <div class="wrap">
        <c:if test="${userRole == 'ROLE_REALTOR'}">
            <div class="title">Мои заказы</div>
        </c:if>
        <c:if test="${userRole != 'ROLE_REALTOR'}">
            <div class="title">Мои помещения</div>
        </c:if>

        <form action="${pageContext.servletContext.contextPath}/user/order-search" method="post">

            <input type="text" name="query">
            <input type="submit" value="Search">
        </form>
        <div class="grid">
            <c:forEach var="room" items="${rooms}">
                <div class="item">
                    <p>Адрес: г.${room.getAddress().getCity()}, ул. ${room.getAddress().getStreet()},
                        д. ${room.getAddress().getHouseNumber()}</p>
                    <p>Количество комнат: ${room.getNumberOfRooms()}</p>
                    <p>Площадь: ${room.getSquare()}</p>
                    <p>Описание: ${room.getDescription()}</p>
                    <p>Цена: ${room.getPrice()}</p>
                    <form method="post" action="${pageContext.servletContext.contextPath}/deleteRoom/${room.id}">
                        <button class="table-button-delete">
                            <img class="table-img" src="${pageContext.servletContext.contextPath}/images/X.svg"/>
                        </button>
                    </form>
                    <form method="get" action="${pageContext.servletContext.contextPath}/updateRoom/${room.id}">
                        <button class="table-button-update">
                            <img class="table-img" src="${pageContext.servletContext.contextPath}/images/update.svg"/>
                        </button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
    </body>
    </html>
</tags:master>