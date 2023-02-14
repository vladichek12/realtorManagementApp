<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Welcome!">
    <h1>
        Welcome!
    </h1>
    <c:if test="${not empty error}">
        <p class="error">There were some problems while logging in!</p>
    </c:if>
    <form method="post" action="${pageContext.servletContext.contextPath}/login">
        <p>
            Login:<input name="login" value="${param.login}" type="text"/>
            <c:if test="${not empty error}">
                <span class="error">
                        ${error}
                </span>
            </c:if>
        </p>
        <p>
            Password:<input name="password" value="${param.password}" type="password"/>
            <c:if test="${not empty error}">
                <span class="error">
                        ${error}
                </span>
            </c:if>
        </p>
        <p>
            <button>Submit</button>
        </p>
    </form>
    <br/>
    <br/>
    <form method="get" action="${pageContext.servletContext.contextPath}/registration">
        <button>
            Sign in
        </button>
    </form>
</tags:master>
