<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-16BE" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:master pageTitle="Welcome!" word="Недвижимость">
    <head>
        <title>Realtor agency</title>
        <link rel="stylesheet" href="styles/admin.css">
        <script src="js/sort.js"></script>
        <style>
            <%@include file="/styles/admin.css"%>
        </style>
    </head>
    <div class="form">
        <h1 class="title">
            Регистрация
        </h1>
        <form method="post" action="${pageContext.servletContext.contextPath}/registration">
            <p>Введите ваше имя:</p>
            <input name="name" value="${param.name}" type="text"/>

            <p>Введите ваш логин:</p>
            <input name="login" value="${param.login}" type="text"/>
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>
            <p>Введите ваш пароль:</p>
            <input name="password" value="${param.password}" type="text"/>
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>
            <p>Введите ваш номер телефона:</p>
            <input name="phoneNumber" value="${param.phoneNumber}" type="text"/>
            </br>
            <ul class="checkbox-ul">
                <li class="li-text">
                    <div>Клиент</div>
                </li>
                <li class="li-text" style="padding-right: 40px">
                    <div>Риелтор</div>
                </li>
            </ul>
            <ul class="checkbox-ul">
                <li class="checkbox-li"><input class="check" type="radio" id="customer" name="role" value="customer"
                                               checked/></li>
                <li class="checkbox-li"><input class="check" type="radio" id="realtor" name="role" value="realtor"/>
                </li>
            </ul>
            <button class="registration-button">Зарегистрироваться</button>
        </form>
    </div>

</tags:master>