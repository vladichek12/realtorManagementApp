<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-16BE" %>
<%@ page import="java.util.Base64" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<tags:master word="Недвижимость" pageTitle="Связаться">
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

    <div class="wrap">
        <div class="title">Информация о помещении</div>
        <table class="main-table">
            <tr>
                <td>Город</td>
                <td>Улица</td>
                <td>Номер здания/квартиры/дома</td>
                <td>Кол-во комнат</td>
                <td>Площадь</td>
                <td>Тип</td>
                <td>Цена</td>
            </tr>
            <tbody>
            <tr>
                <td>${room.getAddress().getCity()}</td>
                <td>${room.getAddress().getStreet()}</td>
                <td>${room.getAddress().getHouseNumber()}</td>
                <td>${room.getNumberOfRooms()}</td>
                <td>${room.getSquare()}</td>
                <td>${room.getType()}</td>
                <td>${room.getPrice()}$</td>
            </tr>
            </tbody>
        </table>
        <div class="grid">
            <div class="item">
                <div class="images">
                    <c:forEach var="image" items="${room.getRoomImage()}">
                        <img  style="max-height: 300px" src="data:${image.getType()};base64, ${fn:escapeXml(Base64.getEncoder().encodeToString(image.getImage()))}" />
                    </c:forEach>
                </div>
            </div>
        </div>
        <h1 style="color: #46b1d0">
            Описание:
        </h1>
        <h2 style="font-style: italic;font-size:30px;color: white">
            ${room.getDescription()}
        </h2>
        <h1 style="color: #46b1d0">
            Контакты владельца:&emsp;<span style="color: white">${room.getUser().getPhoneNumber()}</span>
        </h1>
        <c:if test="${not empty sessionScope.currentUser}">
            <form method="get" action="${pageContext.servletContext.contextPath}/connect/${room.id}">
                <button class="grid-button" style="font-size: 25px">Оформить заказ</button>
            </form>
        </c:if>
    </div>
    </body>
    </html>
</tags:master>