<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>DreamJob</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="resource.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.error404" var="error_404"/>

    <link rel="stylesheet" href="css/error.css">
</head>
<body>
<div class="error404">404</div>
<p class="errtext">${error_404}</p>
</body>
</html>


