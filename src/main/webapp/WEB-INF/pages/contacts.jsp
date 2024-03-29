<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-16BE" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page isErrorPage="true" %>

<tags:homeHeader pageTitle="Home"
                 mainWord="Недвижимость"
                 aboutUs="О нас"
                 catalog="Каталог"
                 contacts="Контакты"
                 signIn="Вход"
                 signUp="Регистрация">
    <style>
        <%@include file="/styles/main.css"%>
    </style>
    <body background="images/background.jpg">
    <div class="main-design-contacts">
        <h1>
            Контакты
        </h1>
        <h2>
            <div>
                (A1) +375291234567
            </div>
            <div>
                Inst: @superRealtors
            </div>
            <div>
                Mail: realtor-management-app@mail.ru
            </div>
        </h2>
    </div>

    </body>
</tags:homeHeader>