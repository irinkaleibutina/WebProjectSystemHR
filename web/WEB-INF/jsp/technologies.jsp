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
    <fmt:message bundle="${loc}" key="locale.technologies" var="technologies"/>
    <fmt:message bundle="${loc}" key="locale.back" var="back"/>
    <fmt:message bundle="${loc}" key="locale.change" var="change"/>

    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/technologies.css">

</head>

<body>
<div class="vacancies">${technologies}</div>
<form name=" skills" method="post" action="Controller">
    <input type="hidden" name="command" value="add_skill">
    <c:if test="${technology != null}">
    <div id="all_vac" class="clearfix">
        <c:forEach items="${technology}" var="item">
        <div class="technology">
            <div class="vacancies"> ${item.title}
                <input id="${item.title}" type="checkbox" name="${item.title}"/>
            </div>
        </div>
        <c:forEach items="${sessionScope.applicant.skills}" var="vacancy" varStatus="loop">
        <c:if test="${vacancy.title == item.title}">
        <script>
            document.getElementById("${item.title}").checked = true;
        </script>
        </c:if>
        </c:forEach>
        </c:forEach>
        <button class="submit after-box" type="submit" style="clear: both">
                ${change}
        </button>
        <button class="submit after-box" type="submit" style="clear: both">
                ${back}
        </button>
        </c:if>
</form>
</body>
</html>
