<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Welcome!">
    <head>
        <title>Realtor agency</title>
        <link rel="stylesheet" href="styles/admin.css">
        <script src="js/sort.js"></script>
    </head>

    <c:if test="${not empty error}">
        <p class="error">There were some problems while logging in!</p>
    </c:if>
    <div class="form">
        <h1 class="title">
            Welcome! Log in please!
        </h1>
        <form method="post" action="${pageContext.servletContext.contextPath}/login">
            <p> Login:</p>
            <input name="login" value="${param.login}" type="text"/>
            <c:if test="${not empty error}">
                <p class="error">
                        ${error}
                </p>
            </c:if>

            <p>Password:</p>
            <input name="password" value="${param.password}" type="password"/>
            <c:if test="${not empty error}">
                <p class="error">
                        ${error}
                </p>
            </c:if>
            </br>
            </br>
            <button class="submit-button">Submit</button>
        </form>
        <br/>
        <div class="or">or</div>
        <br/>
        <form method="get" action="${pageContext.servletContext.contextPath}/registration">
            <button class="sign-in-button">
                Sign in
            </button>
        </form>
    </div>

</tags:master>