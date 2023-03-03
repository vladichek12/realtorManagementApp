<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:master pageTitle="Welcome!">
    <head>
        <title>Realtor agency</title>
        <link rel="stylesheet" href="styles/admin.css">
        <script src="js/sort.js"></script>
    </head>
    <div class="form">
        <h1 class="title">
            Registration
        </h1>
        <c:if test="${not empty error}">
            <p class="error">
                There were some problems while registration!
            <div class="error">
                    ${error}
            </div>
            </p>
        </c:if>
        <form method="post" action="${pageContext.servletContext.contextPath}/registration">
            <p>Enter your name:</p>
            <input name="name" value="${param.name}" type="text"/>

            <p>Enter your login:</p>
            <input name="login" value="${param.login}" type="text"/>
            <p>Enter your password:</p>
            <input name="password" value="${param.password}" type="text"/>
            </br>
            <ul class="checkbox-ul">
                <li class="li-text">
                    <div>Customer</div>
                </li>
                <li class="li-text">
                    <div>Realtor</div>
                </li>
            </ul>
            <ul class="checkbox-ul">
                <li class="checkbox-li"><input class="check" type="radio" id="customer" name="role" value="customer"
                                               checked/></li>
                <li class="checkbox-li"><input class="check" type="radio" id="realtor" name="role" value="realtor"/>
                </li>
            </ul>


            <button class="submit-button">Register</button>
        </form>
    </div>

</tags:master>