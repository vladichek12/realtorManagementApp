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
    <ul>
        <li><a href="${pageContext.servletContext.contextPath}/admin/customers">Клиенты</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/realtors">Риелторы</a></li>
        <li><a class="active">Заказы</a></li>
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
                <div class="item">
                    <p>
                        <img src="data:${room.getRoomImage().getType()};base64, ${fn:escapeXml(Base64.getEncoder().encodeToString(room.getRoomImage().getImage()))}"
                             class="_image"/>
                    </p>
                    <p>Описание: ${room.getDescription()}</p>
                    <p>Тип: ${room.getType()}</p>
                    <p>Адрес: г.${room.getAddress().getCity()}, ул. ${room.getAddress().getStreet()},
                        д. ${room.getAddress().getHouseNumber()}</p>
                    <p>Количество комнат: ${room.getNumberOfRooms()}</p>
                    <p>Площадь: ${room.getSquare()}</p>
                    <p>Цена: ${room.getPrice()}</p>
                    <p>Контакты: ${room.getUser().getPhoneNumber()}</p>
                    <form method="get" action="${pageContext.servletContext.contextPath}/adminUpdateOrder/${room.id}">
                        <button class="registration-button">Изменить</button>
                    </form>
                    <form method="post" action="${pageContext.servletContext.contextPath}/admin/orders">
                        <button class="submit-button" type="submit" name="userId" value="${room.getId()}">
                            Удалить
                        </button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>

    </div>
    </div>
    </body>
    </html>
</tags:master>