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
    <div class="main-welcome">
        <h1>
            Добро пожаловать
        </h1>
    </div>
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
</main>

</body>