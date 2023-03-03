<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="true" %>
<%@ attribute name="word" required="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<html>
<tags:header pageTitle="${pageTitle}" word="${word}"/>
<main>
    <jsp:doBody/>
</main>
</html>