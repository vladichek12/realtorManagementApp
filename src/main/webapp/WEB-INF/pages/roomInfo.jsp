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
                <td>${room.getPrice()}</td>
            </tr>
            </tbody>
        </table>
        <p>
            <img src="data:${room.getRoomImage().getType()};base64, ${fn:escapeXml(Base64.getEncoder().encodeToString(room.getRoomImage().getImage()))}"
                 class="_image"/>
        </p>
        <h1>
            Описание:
        </h1>
        <h2>
            ${room.getDescription()}
        </h2>
        <h1>
            Контакты владельца:&emsp;${room.getUser().getPhoneNumber()}
        </h1>
    </div>
    </body>
    </html>
</tags:master>