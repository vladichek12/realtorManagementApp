<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-16BE" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.Base64" %>
<%@ page import="realtorManagementApp.services.impl.RoomServiceImpl" %>
<%@ page import="java.util.stream.Collectors" %>

<head>
    <title>
        Недвижимость
    </title>
    <link rel="icon" href="${pageContext.request.contextPath}/images/icon.png"/>
</head>
<style>
    <%@include file="/styles/main.css"%>
</style>
<body>
<header>
    <a href="${pageContext.servletContext.contextPath}/">
        <img class="scale" src="${pageContext.request.contextPath}/images/logo.svg" alt="logo"/>
        <a style=" text-align:left;
                    font-size: 65px;
                    font-weight: bold">
            Недвижимость
        </a>
    </a>
    <nav style="padding-left: 400px">
        <a href="#catalog">
            Каталог
        </a>
        <a href="#aboutUs">
            О нас
        </a>
        <a href="${pageContext.servletContext.contextPath}/login">
            Вход
        </a>
    </nav>
</header>
<main>
    <div class="main-welcome" style="background-image: url('images/background2.JPG');">
        <h1>
            Добро пожаловать
            <form action="#catalog">
                <button class="button-for-index">
                    Подробнее
                </button>
            </form>
        </h1>
    </div>

    <h1 style="color: #46b1d0;text-align: center;font-size: 45px;margin-top: 5%">
        О нас
    </h1>

    <div class="main-info" id="aboutUs">

        <h4>
            <div style="color: #46b1d0; font-size: 32px">
                Мы стремимся
            </div>
            <br/>
            быть в курсе всех новых трендов на рынке недвижимости и всегда готовы адаптироваться к
            изменяющимся
            условиям. Мы постоянно совершенствуем нашу работу, обучаем нашу команду и следим за качеством
            предоставляемых
            услуг.
        </h4>
        <h4>
            <div style="color: #46b1d0; font-size: 32px">
                Мы верим
            </div>
            <br/>
            - наш успех заключается не только в том, что мы предлагаем лучшие объекты недвижимости и
            высокий
            уровень сервиса, но и в нашей преданности и открытости к нашим клиентам. Мы всегда готовы выслушать вас,
            ответить на ваши вопросы и помочь вам сделать правильный выбор.
        </h4>
        <h4>
            <div style="color: #46b1d0; font-size: 32px">
                Мы предлагаем
            </div>
            <br/>
            Если вы ищете надежного партнера в сфере недвижимости, который поможет вам найти идеальное жилье или
            поможет
            вам
            инвестировать в недвижимость, то наша компания - идеальный выбор.
        </h4>
        <h4>
            Свяжитесь с нами сегодня, и мы готовы начать
            работу над вашим проектом прямо сейчас!
            <br/>
            <br/>
            <span style="font-weight: bold;color: #46b1d0">
                (A1) +375291234567
            </span>
            <br>
            <span style="font-weight: bold;color: #46b1d0">
                Inst: @superRealtors
            </span>
            <br>
            <span style="font-weight: bold;color: #46b1d0">
                Mail: realtor-management-app@mail.ru
            </span>
        </h4>
    </div>
    <div class="main-catalog" id="catalog">
        <h1>Горячие предложения</h1>
        <div class="grid">
            <c:forEach var="room"
                       items="<%=RoomServiceImpl.getInstance().findAll().stream().limit(8).collect(Collectors.toList()) %>">
                <div class="item" data-room-id="${room.getId()}">
                    <div class="image">
                        <c:set var="image" value="${room.getFirstImage()}"/>
                        <img src="data:${image.getType()};base64, ${fn:escapeXml(Base64.getEncoder().encodeToString(image.getImage()))}"/>
                    </div>
                    <div class="description">${room.getDescription()}</div>
                    <div class="price">Цена:${room.getPrice()}$</div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
</body>