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
    <head>
        <style>
            <%@include file="/styles/main.css"%>
        </style>
    </head>
    <body background="images/background.jpg" class="aboutUs">
    <div class="main-design">
        <h1>
        О нас
    </h1>
        <h4 >
            <p>
                Мы стремимся быть в курсе всех новых трендов на рынке недвижимости и всегда готовы адаптироваться к
                изменяющимся
                условиям. Мы постоянно совершенствуем нашу работу, обучаем нашу команду и следим за качеством
                предоставляемых
                услуг.
            </p>
            <p>
                Мы верим, что наш успех заключается не только в том, что мы предлагаем лучшие объекты недвижимости и высокий
                уровень сервиса, но и в нашей преданности и открытости к нашим клиентам. Мы всегда готовы выслушать вас,
                ответить на ваши вопросы и помочь вам сделать правильный выбор.

                Если вы ищете надежного партнера в сфере недвижимости, который поможет вам найти идеальное жилье или поможет
                вам
                инвестировать в недвижимость, то наша компания - идеальный выбор.
            </p>
            <p>
                Свяжитесь с нами сегодня, и мы готовы начать
                работу над вашим проектом прямо сейчас!
            </p>
        </h4></div>

    </body>
</tags:homeHeader>