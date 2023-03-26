<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-16BE" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
    <ul class="navigator">
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
            <div class="user-title">Мои заказы</div>
        </c:if>
        <c:if test="${userRole != 'ROLE_REALTOR'}">
            <div class="user-title">Мои помещения</div>
        </c:if>

        <form action="${pageContext.servletContext.contextPath}/orders/search" method="post">

            <ul class="search-ul">
                <li class="search-li"><input type="text" name="query" value="${param.query}"></li>
                <li class="search-button"><input type="submit" value="Поиск"></li>
            </ul>
            <ul class="filters-ul">
                <li class="filters-li">
                    <label><input type="checkbox" name="filter" value="FLAT"> Квартиры</label>
                </li>
                <li class="filters-li">
                    <label><input type="checkbox" name="filter" value="HOUSE"> Дома</label>
                </li>
                <li class="filters-li">
                    <label><input type="checkbox" name="filter" value="COTTAGE"> Коттеджи</label>
                </li>
                <li class="filters-li">
                    <label><input type="checkbox" name="filter" value="ROOM"> Комнаты</label>
                </li>
            </ul>
        </form>
        <div class="grid">
            <c:forEach var="room" items="${rooms}">
                <div class="item" data-room-id="${room.getId()}">
                    <div class="image">
                        <c:set var="image" value="${room.getFirstImage()}"/>
                        <img src="data:${image.getType()};base64, ${fn:escapeXml(Base64.getEncoder().encodeToString(image.getImage()))}" />

                    </div>
                    <div class="description">${room.getDescription()}</div>
                    <div class="price">Цена:${room.getPrice()}$</div>
                    <c:if test="${userRole != 'ROLE_REALTOR'}">
                        <form method="post" action="${pageContext.servletContext.contextPath}/deleteRoom/${room.id}">
                            <button class="grid-button">
                                Удалить
                            </button>
                        </form>
                    </c:if>
                    <c:if test="${userRole != 'ROLE_REALTOR'}">
                        <form method="get" action="${pageContext.servletContext.contextPath}/updateRoom/${room.id}">
                            <button class="grid-button">
                                Изменить
                            </button>
                        </form>
                    </c:if>
                    <c:if test="${userRole == 'ROLE_REALTOR' && room.getStatus() == 'Processing'}">
                        <form method="post" action="${pageContext.servletContext.contextPath}/deleteRoom/${room.id}">
                            <button class="grid-button">
                                Закрыть сделку
                            </button>
                        </form>
                    </c:if>
                </div>
            </c:forEach>
        </div>
        <script>
            const gridItems = document.querySelectorAll('.item');
            gridItems.forEach(item => {
                item.addEventListener('click', event => {
                    const roomId = item.getAttribute('data-room-id');
                    const form = document.createElement('form');
                    form.method = 'GET';
//const url = `${window.location.origin}${pageContext.request.contextPath}/roomDetails?id=${roomId}`;
                    form.action = `${pageContext.request.contextPath}/roomDetails`;
                    const input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = 'id';
                    input.value = roomId;
                    form.appendChild(input);
                    document.body.appendChild(form);
                    form.submit();
                });
            });
        </script>
    </div>
    </body>
    </html>
</tags:master>