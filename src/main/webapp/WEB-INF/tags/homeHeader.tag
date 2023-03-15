<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="true" %>
<%@ attribute name="mainWord" required="true" %>
<%@ attribute name="catalog" required="true" %>
<%@ attribute name="aboutUs" required="true" %>
<%@ attribute name="contacts" required="true" %>
<%@ attribute name="signIn" required="true" %>
<%@ attribute name="signUp" required="true" %>


<head>
    <title>${pageTitle}</title>
    <link href='http://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
</head>
<body class="product-list">
<header>
    <a href="${pageContext.servletContext.contextPath}/">
        <img src="${pageContext.servletContext.contextPath}/images/logo.svg"/>
        ${mainWord}
    </a>
    <span class="references">
    &emsp;&emsp;&emsp;&emsp;&emsp;
        &emsp;&emsp;
        <a href="${pageContext.servletContext.contextPath}/rooms">
            ${catalog}
        </a>
        &emsp;&emsp;
        <a href="${pageContext.servletContext.contextPath}/info">
            ${aboutUs}
        </a>
        &emsp;&emsp;
        <a href="${pageContext.servletContext.contextPath}/contacts">
            ${contacts}
        </a>
        &emsp;&emsp;
        <a href="${pageContext.servletContext.contextPath}/login">
            ${signIn}
        </a>
        &emsp;&emsp;
        <a href="${pageContext.servletContext.contextPath}/registration">
            ${signUp}
        </a>
    </span>
</header>
<main>
    <jsp:doBody/>
</main>