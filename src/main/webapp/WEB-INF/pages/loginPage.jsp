<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-16BE" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Добро пожаловать!" word="Недвижимость">
    <head>
        <title>Realtor agency</title>
        <link rel="stylesheet" href="styles/admin.css">
        <script src="js/sort.js"></script>
        <style>
            <%@include file="/styles/admin.css"%>
        </style>
    </head>

    <body background="images/background2.JPG">
        <div class="form">
            <h1 class="title">
                Добро пожаловать!
            </h1>
            <form method="post" action="${pageContext.servletContext.contextPath}/login">
                <p> Логин:</p>
                <input name="login" value="${param.login}" type="text"/>
                <c:if test="${not empty error}">
                    <p class="error">
                            ${error}
                    </p>
                </c:if>

                <p>Пароль:</p>
                <input name="password" value="${param.password}" type="password"/>
                <c:if test="${not empty error}">
                    <p class="error">
                            ${error}
                    </p>
                </c:if>
                </br>
                </br>
                <button class="submit-button">Войти</button>
            </form>
            <br/>
            <div class="or">или</div>
            <br/>
            <form method="get" action="${pageContext.servletContext.contextPath}/registration">
                <button class="sign-in-button">
                    Регистрация
                </button>
            </form>
        </div>
    </body>

</tags:master>