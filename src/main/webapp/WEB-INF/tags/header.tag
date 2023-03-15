<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="true" %>
<%@ attribute name="word" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>${pageTitle}</title>
    <link href='http://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
    <style>
        <%@include file="/styles/main.css"%>
    </style>
    <script>
        function changeColor(divId) {
            var div = document.getElementById(divId);
            div.style.color = "red";

        }
    </script>
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
                    <a href="${pageContext.servletContext.contextPath}/user" onclick="changeColor('myDiv1')">
                        <img src="${pageContext.servletContext.contextPath}/images/profile.svg"/>
                        <div class="nickname" id="myDiv1"> ${user.name}</div>
                    </a>

            </c:if>
            <c:if test="${user.getUserRole() == 'ROLE_ADMIN'}">
                <a href="${pageContext.servletContext.contextPath}/admin/customers" onclick="changeColor('myDiv2')">
                    <img src="${pageContext.servletContext.contextPath}/images/profile.svg"/>
                    <div class="nickname" id="myDiv2"> ${user.name}</div>
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