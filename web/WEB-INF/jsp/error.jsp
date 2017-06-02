<%--
  Created by IntelliJ IDEA.
  User: irinaleibutina
  Date: 08.03.17
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>DreamJob</title>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="resource.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.fail_take_vac" var="fail_take_vac"/>
    <fmt:message bundle="${loc}" key="locale.fail_take_appl" var="fail_take_appl"/>
    <fmt:message bundle="${loc}" key="locale.fail_login" var="fail_login"/>
    <fmt:message bundle="${loc}" key="locale.fail_registration" var="fail_registration"/>
    <fmt:message bundle="${loc}" key="locale.fail_vacancy" var="fail_vacancy"/>
    <fmt:message bundle="${loc}" key="locale.syserror" var="syserror"/>
    <fmt:message bundle="${loc}" key="locale.fail_search_vac" var="fail_search_vac"/>
    <fmt:message bundle="${loc}" key="locale.fail_get_news" var="fail_get_news"/>
    <fmt:message bundle="${loc}" key="locale.take_news" var="fail_take_news"/>
    <fmt:message bundle="${loc}" key="locale.fail_search_news" var="fail_search_news"/>

    <link rel="stylesheet" href="css/error.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div class="error">
    <c:if test="${requestScope.fail_take_vac!= null}">
        ${fail_take_vac}
    </c:if>

    <c:if test="${requestScope.fail_take_appl!= null}">
        ${fail_take_appl}
    </c:if>

    <c:if test="${requestScope.fail_login!= null}">
        ${fail_login}
    </c:if>

    <c:if test="${requestScope.fail_search_vac!= null}">
        ${fail_search_vac}
    </c:if>

    <c:if test="${requestScope.fail_get_news!= null}">
        ${fail_get_news}
    </c:if>

    <c:if test="${requestScope.fail_registration!= null}">
        ${fail_registration}
    </c:if>

    <c:if test="${requestScope.fail_vacancy!= null}">
        ${fail_vacancy}
    </c:if>

    <c:if test="${requestScope.take_news!= null}">
        ${fail_take_news}
    </c:if>

    <c:if test="${requestScope. fail_search_news!= null}">
        ${fail_search_news}
    </c:if>

    <c:if test="${requestScope.error!= null}">
        ${syserror}
    </c:if>
</div>
<div id="f1" style="clear: both">
    <nav>
        <ul class="nav">
            <li><a href="Controller?command=main_page" class="logo">DreamJob</a></li>
            <li class="copyright">©${copyright}</li>
        </ul>
    </nav>
</div>
</body>
</html>
