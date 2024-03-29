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
    <ul class="navigator">
        <li><a href="${pageContext.servletContext.contextPath}/admin/customers">Клиенты</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/realtors">Риелторы</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/orders">Заказы</a></li>
        <li><a class="active">Объявления</a></li>
    </ul>
    <div>
        <div class="title">Заказы</div>

        <form action="order-search" method="post">
            <ul class="search-ul">
                <li class="search-li"><input type="text" name="query"></li>
                <li class="search-button"><input type="submit" value="Поиск"></li>
            </ul>
            <ul class="filters-ul">
                <li class="filters-li">
                    <label><input type="checkbox" name="filter" value="flat"> Квартиры</label>
                </li>
                <li class="filters-li">
                    <label><input type="checkbox" name="filter" value="house"> Дома</label>
                </li>
                <li class="filters-li">
                    <label><input type="checkbox" name="filter" value="cottage"> Коттеджи</label>
                </li>
                <li class="filters-li">
                    <label><input type="checkbox" name="filter" value="room"> Комнаты</label>
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
                    <p><span style="font-weight: bold">Владелец:</span> ${room.getUser().getName()}</p>
                    <p><span style="font-weight: bold">Риелтор:</span> ${room.getRealtor().getName()}</p>
                    <form method="get" action="${pageContext.servletContext.contextPath}/updateRoom/${room.id}">
                        <button class="grid-button">Изменить</button>
                    </form>
                    <form method="post" action="${pageContext.servletContext.contextPath}/admin/orders">
                        <button class="grid-button" type="submit" name="userId" value="${room.getId()}">
                            Удалить
                        </button>
                    </form>
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