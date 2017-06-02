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

        .news_decoration {
            float: left;
            margin: 5px;
            padding: 15px;
            width: 340px;
            height: auto;
            border: 3px solid #FFA562;
        }
    </style>
</head>

<body>
<c:choose>
    <c:when test="${news_list.isEmpty()}">
    </c:when>
    <c:otherwise>
        <div class="vacancies">${news}</div>
        <div id="all_vac" class="clearfix">
            <c:forEach items="${news_list}" var="vacancy" varStatus="loop">
                <div class="news_decoration">
                    <h3 class="title" align="center"
                        onClick="location.href='Controller?command=get_news&item=${vacancy.newsId}'">${vacancy.newsTitle}</h3>
                    <c:choose>
                        <c:when test="${sessionScope.employee != null}">
                            <button class="submit"
                                    onclick="location.href='#${vacancy.newsId}'">
                                    ${delete}
                            </button>
                        </c:when>
                    </c:choose>
                </div>
                <div id="${vacancy.newsId}" class="modalDialog delete_vac">
                    <div>
                        <a href="#close" title="Закрыть" class="close">X</a>
                        <div class="vacancies">${delete_news}</div>
                        <button class="submit"
                                onclick="location.href='Controller?command=delete_news&item=${vacancy.newsId}'">
                                ${delete}
                        </button>
                        <button class="submit" onclick="location.href='#close'">${close}</button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
