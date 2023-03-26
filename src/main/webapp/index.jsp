<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-16BE" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.Base64" %>
<%@ page import="realtorManagementApp.services.impl.RoomServiceImpl" %>
<%@ page import="java.util.stream.Collectors" %>


<%--<tags:homeHeader pageTitle="Home"--%>
<%--                 mainWord="Недвижимость"--%>
<%--                 aboutUs="О нас"--%>
<%--                 catalog="Каталог"--%>
<%--                 contacts="Контакты"--%>
<%--                 signIn="Вход"--%>
<%--                 signUp="Регистрация">--%>

<%--</tags:homeHeader>--%>

<head>
    <title>
        Недвижимость
    </title>
</head>
<style>
    <%@include file="/styles/main.css"%>
</style>
<body>
<header>
    <a href="${pageContext.servletContext.contextPath}/">
        <img src="${pageContext.request.contextPath}/images/logo.svg" alt="logo"/>
        <%-- ${mainWord}--%>
    </a>
    <nav>
        <a href="<%--${pageContext.servletContext.contextPath}/rooms--%>#catalog">
            Каталог
        </a>
        <a href="<%--${pageContext.servletContext.contextPath}/info--%>#aboutUs">
            О нас
        </a>
        <a href="${pageContext.servletContext.contextPath}/login">
            Вход
        </a>
    </nav>
</header>
<main>
    <div class="main-welcome">
        <h1>
            Добро пожаловать
        </h1>
    </div>
    <%--<div class="main-design">--%>
    <%--    <h2>--%>
    <%--        Мы--%>
    <%--    </h2>--%>
    <%--    <p>--%>
    <%--        Являемся одним из лидеров на рынке недвижимости и готовы предложить вам самые выгодные и уникальные варианты.--%>
    <%--        Наша компания имеет многолетний опыт работы в этой сфере и мы готовы помочь вам с любыми вопросами, связанными с недвижимостью.--%>
    <%--        Мы предоставляем широкий спектр услуг, начиная от подбора объектов до сопровождения сделки.--%>
    <%--    </p>--%>
    <%--</div>--%>
    <%--<div class="main-design">--%>
    <%--    <h2>--%>
    <%--        У нас--%>
    <%--    </h2>--%>
    <%--    <p>--%>
    <%--        Вы найдете самый полный каталог недвижимости, который позволит вам найти идеальный дом или квартиру, которые соответствуют вашим потребностям и бюджету.--%>
    <%--        Наша база данных включает в себя объекты разных типов и ценовых категорий, начиная от уютных квартир в центре города до роскошных вилл на побережье.--%>

    <%--        Мы работаем только с проверенными и надежными застройщиками и владельцами недвижимости, что позволяет нам гарантировать высокое качество объектов и безопасность сделки.--%>
    <%--    </p>--%>
    <%--</div>--%>
<%--    <div class="wrap" id="catalog">
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
    </div>--%>


    <div class="main-catalog" id="catalog">
        <h1>Горячие предложения</h1>
        <div class="grid">
            <c:forEach var="room" items="<%=RoomServiceImpl.getInstance().findAll().stream().limit(8).collect(Collectors.toList()) %>">
                <div class="item" data-room-id="${room.getId()}">
                    <div class="image">
                        <c:set var="image" value="${room.getFirstImage()}"/>
                        <img src="data:${image.getType()};base64, ${fn:escapeXml(Base64.getEncoder().encodeToString(image.getImage()))}" />
                    </div>
                    <div class="description">${room.getDescription()}</div>
                    <div class="price">Цена:${room.getPrice()}$</div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="main-info" id="aboutUs">
        <h1>
            О нас
        </h1>
        <h4 >

                Мы стремимся быть в курсе всех новых трендов на рынке недвижимости и всегда готовы адаптироваться к
                изменяющимся
                условиям. Мы постоянно совершенствуем нашу работу, обучаем нашу команду и следим за качеством
                предоставляемых
                услуг.
            <br>

                Мы верим, что наш успех заключается не только в том, что мы предлагаем лучшие объекты недвижимости и высокий
                уровень сервиса, но и в нашей преданности и открытости к нашим клиентам. Мы всегда готовы выслушать вас,
                ответить на ваши вопросы и помочь вам сделать правильный выбор.
            <br>
                Если вы ищете надежного партнера в сфере недвижимости, который поможет вам найти идеальное жилье или поможет
                вам
                инвестировать в недвижимость, то наша компания - идеальный выбор.
            <br>
                Свяжитесь с нами сегодня, и мы готовы начать
                работу над вашим проектом прямо сейчас!
            <br>
        </h4>
        <h4>
            (A1) +375291234567
            <br>
            Inst: @superRealtors
            <br>
            Mail: realtor-management-app@mail.ru
        </h4>
    </div>

<%--    <div class="main-design-contacts" id="contacts">--%>
<%--        <h1>--%>
<%--            Контакты--%>
<%--        </h1>--%>
<%--        <h2>--%>

<%--                (A1) +375291234567--%>

<%--                Inst: @superRealtors--%>

<%--                Mail: realtor-management-app@mail.ru--%>

<%--        </h2>--%>
<%--    </div>--%>
</main>

</body>