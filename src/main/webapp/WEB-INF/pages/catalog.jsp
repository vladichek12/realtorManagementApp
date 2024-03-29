<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-16BE" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:homeHeader pageTitle="Home"
                 mainWord="Недвижимость"
                 aboutUs="О нас"
                 catalog="Каталог"
                 contacts="Контакты"
                 signIn="Вход"
                 signUp="Регистрация">
    <html>
    <head>
        <title>Realtor agency</title>
        <link rel="stylesheet" href="styles/admin.css">
        <script src="js/sort.js"></script>
    </head>
    <body background="${pageContext.request.contextPath}/images/background.jpg">

    <div class="wrap">
        <div class="title">Доступные  помещения</div>
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
            <c:forEach var="room" items="${rooms}">
                <tr>
                    <td>${room.getAddress().getCity()}</td>
                    <td>${room.getAddress().getStreet()}</td>
                    <td>${room.getAddress().getHouseNumber()}</td>
                    <td>${room.getNumberOfRooms()}</td>
                    <td>${room.getSquare()}</td>
                    <td>${room.getType()}</td>
                    <td>${room.getPrice()}$</td>
                    <td>
                        <form method="get" action="${pageContext.servletContext.contextPath}/room/${room.getId()}">
                            <button class="grid-button">
                                Просмотреть
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </body>
    </html>
</tags:homeHeader>