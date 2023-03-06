<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<tags:master pageTitle="Realtor management app">--%>
    <head>
        <link rel="stylesheet" href="styles/user.css">
        <script src="js/sort.js"></script>
        <style>
            <%@include file="/styles/admin.css"%>
        </style>
    </head>
    <div class="form">
        <form method="post" action="${pageContext.servletContext.contextPath}/adminUpdateOrder/${room.id}">
            <input type="hidden" value="${room.id}" name="id"/>
            <h2 class="title">Данные о помещении:</h2>

            <tags:roomStringForm room="${room}" name="city" label="Город:"
                                 possibleErrors="${possibleErrors}"></tags:roomStringForm>

            <tags:roomStringForm room="${room}" name="street" label="Улица:"
                                 possibleErrors="${possibleErrors}"></tags:roomStringForm>

            <tags:roomStringForm room="${room}" name="houseNumber" label="Номер дома/квартиры/комнаты:"
                                 possibleErrors="${possibleErrors}"></tags:roomStringForm>

            <tags:roomStringForm room="${room}" name="square" label="Площадь:"
                                 possibleErrors="${possibleErrors}"></tags:roomStringForm>

            <tags:roomStringForm room="${room}" name="numberOfRooms" label="Кол-во комнат:"
                                 possibleErrors="${possibleErrors}"></tags:roomStringForm>
            <tags:roomStringForm room="${room}" name="description" label="Описание:"
                                 possibleErrors="${possibleErrors}"></tags:roomStringForm>
            <tags:roomStringForm room="${room}" name="price" label="Цена:"
                                 possibleErrors="${possibleErrors}"></tags:roomStringForm>

            <button class="submit-button">Обновить</button>
        </form>
    </div>

<%--</tags:master>--%>