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
    <fmt:message bundle="${loc}" key="locale.phone" var="phone"/>

    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/signin.css">
    <link rel="stylesheet" href="css/display_element.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<c:if test="${admins_list != null}">
    <c:forEach items="${admins_list}" var="item">
        <div class="border">
            <label>
                <span>${name}</span>
                    ${item.name} ${item.surname}
            </label>

            <label>
                <span>${email}</span>
                    ${item.email}
            </label>

            <label>
                <span>${phone}</span>
                    ${item.phoneNumber}
            </label>
            <label>
                <span>workId</span>
                    ${item.workId}
            </label>
        </div>
    </c:forEach>
</c:if>
<div id="f" style="clear: both">
    <%@ include file="/WEB-INF/jsp/footer.jsp" %>
</div>
</body>
</html>


