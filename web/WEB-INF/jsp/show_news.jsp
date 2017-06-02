<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>DreamJob</title>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="resource.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.vacancies" var="vacancies"/>
    <fmt:message bundle="${loc}" key="locale.news" var="news"/>
    <fmt:message bundle="${loc}" key="locale.aboutCompany" var="about"/>
    <fmt:message bundle="${loc}" key="locale.signOut" var="signOut"/>

    <fmt:message bundle="${loc}" key="locale.add" var="add"/>
    <fmt:message bundle="${loc}" key="locale.delete" var="delete"/>
    <fmt:message bundle="${loc}" key="locale.applicants" var="applicants"/>
    <fmt:message bundle="${loc}" key="locale.apply" var="apply"/>
    <fmt:message bundle="${loc}" key="locale.delete_news" var="delete_news"/>

    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/signin.css">
    <link rel="stylesheet" href="css/display_element.css">
</head>

<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<c:if test="${current_news != null}">
    <div class="vacancy_title" align="center">
        <h3 class="title">${current_news.newsTitle}</h3>
    </div>
    <div class="description" align="center">
        <pre>${current_news.newsDescription}
        </pre>
        <c:choose>
            <c:when test="${sessionScope.employee != null}">
                <button class="submit"
                        onclick="location.href='#deleteNews'">
                        ${delete}
                </button>
            </c:when>
        </c:choose>
    </div>
    <div id="deleteNews" class="modalDialog delete_vac">
        <div>
            <a href="#close" title="Закрыть" class="close">X</a>
            <div class="vacancies">${delete_news}</div>
            <button class="submit"
                    onclick="location.href='Controller?command=delete_news&item=${current_news.newsId}'">
                    ${delete}
            </button>
            <button class="submit" onclick="location.href='#close'">${close}</button>
        </div>
    </div>
    <div id="f" style="clear: both">
        <%@ include file="/WEB-INF/jsp/footer.jsp" %>
    </div>
</c:if>
</body>
</html>
