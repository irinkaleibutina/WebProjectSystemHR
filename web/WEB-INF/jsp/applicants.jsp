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
    <fmt:message bundle="${loc}" key="locale.contacts" var="contacts"/>
    <fmt:message bundle="${loc}" key="locale.preliminary" var="preliminary"/>
    <fmt:message bundle="${loc}" key="locale.technical" var="technical"/>
    <fmt:message bundle="${loc}" key="locale.status" var="status"/>
    <fmt:message bundle="${loc}" key="locale.date" var="date"/>
    <fmt:message bundle="${loc}" key="locale.time" var="time"/>
    <fmt:message bundle="${loc}" key="locale.assign" var="assign"/>
    <fmt:message bundle="${loc}" key="locale.fail_search_appl" var="fail_search_appl"/>
    <fmt:message bundle="${loc}" key="locale.applicants_list" var="applicantslist"/>
    <fmt:message bundle="${loc}" key="locale.status_e" var="status_e"/>
    <fmt:message bundle="${loc}" key="locale.status_f" var="status_f"/>
    <fmt:message bundle="${loc}" key="locale.status_s" var="status_s"/>
    <fmt:message bundle="${loc}" key="locale.dateerror" var="dateerror"/>

    <link rel="stylesheet" href="css/signin.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/table_style.css">
    <link href="jquery-ui.css" rel="stylesheet">
    <link rel="stylesheet" href="css/error.css">
    <link rel="stylesheet" href="css/display_element.css">
</head>

<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<c:if test="${applicants_list != null}">
    <table class="applicant_table" border="1" cellpadding="20" id="ourtable">
        <caption>
            <div class="table_title">${applicantslist}</div>
        </caption>
        <tr class="column_title">
            <th>${name}</th>
            <th>${surname}</th>
            <th>${vacancies}</th>
            <th>${contacts}</th>
            <th>${preliminary}</th>
            <th>${technical}</th>
        </tr>
        <c:forEach items="${applicants_list}" var="item">
            <c:if test="${item.jobVacancy.jobTitle!= null && item.interview.technicalInterview != 'S'
            && item.interview.technicalInterview != 'F'}">
                <tr>
                    <td width="200"><c:out value="${item.name}"/></td>
                    <td width="200"><c:out value="${item.surname}"/></td>
                    <td width="200"><c:out value="${item.jobVacancy.jobTitle}"/></td>
                    <td>
                        <label>
                            <span>${email}</span><br>
                            <c:out value="${item.email}"/>
                        </label>
                        <label>
                            <span>${phone}</span><br>
                            <c:out value="${item.phoneNumber}"/>
                        </label>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${item.interview.preliminaryInterview == 'S'}">
                                <div class="success"><label>${status_s}</label></div>
                            </c:when>
                            <c:otherwise>
                                <form action="Controller" onsubmit="return validatePreInterviewForm()" method="POST">
                                    <input class="input_data" type="hidden" name="command" value="add_pre_interview"/>
                                    <label>
                                        <span>${status}</span>
                                        <select name="status" class="input_data">
                                            <c:choose>
                                                <c:when test="${item.interview.preliminaryInterview  == 'F'}">
                                                    <option value="F">${status_f}</option>
                                                    <option value="S">${status_s}</option>
                                                    <option value="E">${status_e}</option>
                                                </c:when>

                                                <c:otherwise>
                                                    <option value="E">${status_e}</option>
                                                    <option value="S">${status_s}</option>
                                                    <option value="F">${status_f}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </select>
                                    </label>

                                    <label>
                                        <span>${date}</span>
                                        <div class="error-message">${dateerror}</div>
                                        <input type="text" class="input_data datepicker" name="date"
                                               value="${item.interview.datePreInt}">
                                    </label>

                                    <label>
                                        <span>${time}</span>
                                        <input class="input_data" type="time" name="time"
                                               value="${item.interview.timePreInt}">
                                    </label>
                                    <input class="input_data" type="hidden" name="id" value="${item.id}">
                                    <button class="submit" type="submit">
                                            ${assign}
                                    </button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${item.interview.preliminaryInterview != 'E'}">
                                <form action="Controller" method="POST">
                                    <input class="input_data" type="hidden" name="command" value="add_tec_interview"/>
                                    <label>
                                        <span>${status}</span>
                                        <select name="status" class="input_data">
                                            <option value="E">${status_e}</option>
                                            <option value="S">${status_s}</option>
                                            <option value="F">${status_f}</option>
                                        </select>
                                    </label>

                                    <label>
                                        <span>${date} </span>
                                        <input type="text" class="input_data datepicker" name="date"
                                               value="${item.interview.dateTecInt}"/>
                                    </label>

                                    <label>
                                        <span>${time} </span> <input class="input_data" type="time" name="time"
                                                                     value="${item.interview.timeTecInt}">
                                    </label>

                                    <input class="input_data" type="hidden" name="id" value="${item.id}">
                                    <button class="submit" type="submit">
                                            ${assign}
                                    </button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <label>${technical}:<br>${status_e}</label>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</c:if>
<c:if test="${applicants_list == null}">
    <div class="error">
            ${fail_search_appl}
    </div>
</c:if>
<div id="f" style="clear: both">
    <%@ include file="/WEB-INF/jsp/footer.jsp" %>
</div>
<script src="external/jquery/jquery.js"></script>
<script src="jquery-ui.js"></script>
<script>
    $.datepicker.regional.ru = {
        closeText: "Закрыть",
        prevText: "&#x3C;Пред",
        nextText: "След&#x3E;",
        currentText: "Сегодня",
        monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
            "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
        monthNamesShort: ["Янв", "Фев", "Мар", "Апр", "Май", "Июн",
            "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"],
        dayNames: ["воскресенье", "понедельник", "вторник", "среда", "четверг", "пятница", "суббота"],
        dayNamesShort: ["вск", "пнд", "втр", "срд", "чтв", "птн", "сбт"],
        dayNamesMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"],
        weekHeader: "Нед",
        dateFormat: "yy-mm-dd",
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: ""
    };

    $.datepicker.regional.eng = {
        dateFormat: "yy-mm-dd"
    };

    $.datepicker.setDefaults($.datepicker.regional['${sessionScope.locale}']);

    $(".datepicker").datepicker();
</script>

</body>
</html>


