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
    <fmt:message bundle="${loc}" key="locale.fail_take_vac" var="fail_take_vac"/>
    <fmt:message bundle="${loc}" key="locale.delete_vac" var="delete_vac"/>


    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/signin.css">
    <style>
        #f {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 50px;
            background: rgba(255, 177, 28, 0.5);
            text-align: center;
        }

        .copyright {
            display: block;
            color: #1c293a;
            text-decoration: none;
            padding: .8em 1.8em;
            font-size: 100%;
            letter-spacing: 3px;
            position: relative;
            text-align: center;
        }

        #all_vac {
            clear: both;
        }

        .title {
            cursor: pointer;
        }

        .vacancy_error {
            text-align: center;
            color: #1c293a;
            text-decoration: none;
            padding: .8em 1.8em;
            text-transform: uppercase;
            font-size: 100%;
            letter-spacing: 3px;
            position: relative;
        }
    </style>
</head>
<body>

<c:if test="${vacancy != null}">
    <div class="vacancies">${vacancies}</div>
    <div id="all_vac" class="clearfix">
        <c:forEach items="${vacancy}" var="vacancy" varStatus="loop">
            <div class="v">
                <h3 class="title" align="center"
                    onClick="location.href='Controller?command=show_vacancy&item=${vacancy.id}'">${vacancy.jobTitle}</h3>
                <div></div>
                <div class="description" align="center">${vacancy.country}, ${vacancy.city}</div>
                <c:choose>
                    <c:when test="${sessionScope.applicant != null}">
                        <button class="submit"
                                onclick="location.href='Controller?command=submit_application&item=${vacancy.id}'">
                                ${apply}
                        </button>
                    </c:when>
                    <c:when test="${sessionScope.employee != null}">
                        <button class="submit" onclick="location.href='#${vacancy.id}'">${delete}</button>
                    </c:when>
                    <c:otherwise>
                        <button class="submit" onclick="location.href='#openRegForm'">
                                ${apply}
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
            <div id="${vacancy.id}" class="modalDialog delete_vac">
                <div>
                    <a href="#close" title="Закрыть" class="close">X</a>
                    <div class="vacancies">${delete_vac}</div>
                    <button class="submit"
                            onclick="location.href='Controller?command=delete_vacancy&item=${vacancy.id}'">
                            ${delete}
                    </button>
                    <button class="submit" onclick="location.href='#close'">${close}</button>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>
<c:if test="${vacancy.isEmpty()}">
    <div class="vacancy_error">
            ${fail_take_vac}
    </div>
</c:if>
</body>
</html>
