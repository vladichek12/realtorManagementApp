<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="true" %>
<%@ attribute name="word" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>${pageTitle}</title>
    <link href='http://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
</head>
<body class="product-list">
<header>
    <a href="${pageContext.servletContext.contextPath}/">
        <img src="${pageContext.servletContext.contextPath}/images/logo.svg"/>
        ${word}
        <c:set var="user" value="${sessionScope.currentUser}"/>
        &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
        <c:if test="${user != null}">
            <c:if test="${user.getUserRole() != 'ROLE_ADMIN'}">
                <a href="${pageContext.servletContext.contextPath}/user">
                    <img src="${pageContext.servletContext.contextPath}/images/profile.svg"/>
                    <div class="nickname">${user.name}</div>
                </a>
            </c:if>
            <c:if test="${user.getUserRole() == 'ROLE_ADMIN'}">
                <a href="${pageContext.servletContext.contextPath}/admin/customers">
                    <img src="${pageContext.servletContext.contextPath}/images/profile.svg"/>
                    <div class="nickname">${user.name}</div>
                </a>
            </c:if>
        </c:if>

        <c:if test="${user!= null}">
            <a href="${pageContext.servletContext.contextPath}/exit">
                <img src="${pageContext.servletContext.contextPath}/images/exit.svg"/>
            </a>
        </c:if>
    </a>
</header>
<main>
    <jsp:doBody/>
</main>