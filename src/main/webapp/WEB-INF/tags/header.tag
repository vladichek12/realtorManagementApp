<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="true" %>
<%@ attribute name="word" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>${pageTitle}</title>
    <link href='https://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
    <link rel="icon" href="${pageContext.request.contextPath}/images/icon.png"/>
    <style>
        <%@include file="/styles/admin.css"%>
    </style>
    <script>
        function changeColor(divId) {
            var div = document.getElementById(divId);
            div.style.color = "red";
        }
    </script>
</head>
<body class="product-list">
<header class="header-border">
    <a href="${pageContext.servletContext.contextPath}/">
        <img class="scale" src="${pageContext.servletContext.contextPath}/images/logo.svg" alt="logo"/>
        <a style=" text-align:left;
                    font-size: 65px;
                    font-weight: bold">
           ${word}
        </a>
        <c:set var="user" value="${sessionScope.currentUser}"/>
        <c:if test="${user != null}">
            <c:if test="${user.getUserRole() != 'ROLE_ADMIN'}">
                    <a style="padding-left: 250px" href="${pageContext.servletContext.contextPath}/user" onclick="changeColor('myDiv1')">
                        <img class="scale" src="${pageContext.servletContext.contextPath}/images/profile.svg" alt="user"/>
                        <a>
                            <div class="nickname" id="myDiv1"> ${user.name}</div>
                        </a>
                    </a>

            </c:if>
            <c:if test="${user.getUserRole() == 'ROLE_ADMIN'}">
                <a style="padding-left: 250px" href="${pageContext.servletContext.contextPath}/admin/customers" onclick="changeColor('myDiv2')">
                    <img class="scale" src="${pageContext.servletContext.contextPath}/images/profile.svg" alt="admin"/>
                    <a>
                        <div class="nickname" id="myDiv2"> ${user.name}</div>
                    </a>
                </a>
            </c:if>
        </c:if>

        <c:if test="${user!= null}">
            <a href="${pageContext.servletContext.contextPath}/exit">
                <img class="scale" src="${pageContext.servletContext.contextPath}/images/exit.svg" alt="exit"/>
            </a>
        </c:if>
    </a>
</header>
<main>
    <jsp:doBody/>
</main>