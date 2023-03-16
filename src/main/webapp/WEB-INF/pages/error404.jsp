<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page isErrorPage="true" %>

<tags:master pageTitle="Error" word="Недвижимость">
    <h1>
        Извините, произошла ошибка
    </h1>
    <h2>
        Вероятно, эта страница не поддерживается
    </h2>
    <h1>${pageContext.errorData.throwable.message}</h1>
    <p>
        Непредвиденная ошибка!
    </p>
</tags:master>