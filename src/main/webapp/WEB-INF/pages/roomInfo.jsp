<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-16BE" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <td>Описание</td>
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
                <td>${room.getDescription()}</td>
                <td>${room.getType()}</td>
                <td>${room.getPrice()}</td>
            </tr>
            </tbody>
        </table>
        <h2>
            Контакты владельца:&emsp;${room.getUser().getPhoneNumber()}
        </h2>
    </div>
    </body>
    </html>
</tags:master>