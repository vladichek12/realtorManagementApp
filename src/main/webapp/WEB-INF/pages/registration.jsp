<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:master pageTitle="Welcome!">
    <h2>
        Registration
    </h2>
    <c:if test="${not empty error}">
        <p class="error">
            There were some problems while registration!
        <div class="error">
                ${error}
        </div>
        </p>
    </c:if>
    <p>
    <form method="post" action="${pageContext.servletContext.contextPath}/registration">
        <p>
            Enter your name:<input name="name" value="${param.name}" type="text"/>
        </p>
        <p>
            Enter your login:<input name="login" value="${param.login}" type="text"/>
        </p>
        <p>
            Enter your password:<input name="password" value="${param.password}" type="text"/>
        </p>
        <p>
            <button>Register</button>
        </p>
    </form>
    </p>
</tags:master>
