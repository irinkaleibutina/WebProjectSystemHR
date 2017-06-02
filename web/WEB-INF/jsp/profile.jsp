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
    <fmt:message bundle="${loc}" key="locale.signIn" var="signIn"/>
    <fmt:message bundle="${loc}" key="locale.search" var="search"/>
    <fmt:message bundle="${loc}" key="locale.lang" var="lang"/>
    <fmt:message bundle="${loc}" key="locale.langRU" var="ru"/>
    <fmt:message bundle="${loc}" key="locale.langENG" var="eng"/>
    <fmt:message bundle="${loc}" key="locale.company" var="company"/>
    <fmt:message bundle="${loc}" key="locale.employees" var="employees"/>
    <fmt:message bundle="${loc}" key="locale.update" var="update"/>
    <fmt:message bundle="${loc}" key="locale.country" var="country"/>
    <fmt:message bundle="${loc}" key="locale.city" var="city"/>
    <fmt:message bundle="${loc}" key="locale.nameerror" var="nameError"/>
    <fmt:message bundle="${loc}" key="locale.surnameerror" var="surnameError"/>
    <fmt:message bundle="${loc}" key="locale.loginerror" var="loginError"/>
    <fmt:message bundle="${loc}" key="locale.passerror" var="passwordError"/>
    <fmt:message bundle="${loc}" key="locale.emailerror" var="emailError"/>
    <fmt:message bundle="${loc}" key="locale.phoneerror" var="phoneError"/>
    <fmt:message bundle="${loc}" key="locale.workIdError" var="workIdError"/>
    <fmt:message bundle="${loc}" key="locale.profile" var="profile"/>
    <fmt:message bundle="${loc}" key="locale.personal_info" var="personal_info"/>
    <fmt:message bundle="${loc}" key="locale.prof_skills" var="prof_skills"/>
    <fmt:message bundle="${loc}" key="locale.application" var="application"/>
    <fmt:message bundle="${loc}" key="locale.interview" var="interview"/>
    <fmt:message bundle="${loc}" key="locale.preliminary" var="preliminary"/>
    <fmt:message bundle="${loc}" key="locale.technical" var="technical"/>
    <fmt:message bundle="${loc}" key="locale.change" var="change"/>
    <fmt:message bundle="${loc}" key="locale.date" var="date"/>
    <fmt:message bundle="${loc}" key="locale.time" var="time"/>
    <fmt:message bundle="${loc}" key="locale.pre_results" var="pre_results"/>
    <fmt:message bundle="${loc}" key="locale.tech_results" var="tech_results"/>
    <fmt:message bundle="${loc}" key="locale.status_e" var="status_e"/>
    <fmt:message bundle="${loc}" key="locale.status_f" var="status_f"/>
    <fmt:message bundle="${loc}" key="locale.status_s" var="status_s"/>
    <fmt:message bundle="${loc}" key="locale.delete_apply" var="delete_apply"/>

    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/display_element.css">
</head>

<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<c:choose>
    <c:when test="${sessionScope.applicant != null}">
        <div class="profile">${profile}</div>
        <div class="personal_data">
            <div class="vacancies">${personal_info}</div>
            <form action="Controller" method="POST">
                <input class="input_data" type="hidden" name="command" value="update_applicant"/>

                <label>
                    <span>${name}</span>
                    <input class="input_data" type="text" name="name" value="${sessionScope.applicant.name}"/>
                </label>

                <label>
                    <span>${surname}</span>
                    <input class="input_data" type="text" name="surname" value="${sessionScope.applicant.surname}"/>
                </label>

                <label>
                    <span>${email}</span>
                    <input class="input_data" type="text" name="email" value="${sessionScope.applicant.email}"/>
                </label>

                <label>
                    <span>${phone}</span>
                    <input class="input_data" type="text" name="phone" value="${sessionScope.applicant.phoneNumber}"/>
                </label>

                <label>
                    <span>${country}</span>
                    <input class="input_data" type="text" name="country" value="${sessionScope.applicant.country}"/>
                </label>
                <label>
                    <span>${city}</span>
                    <input class="input_data" type="text" name="city" value="${sessionScope.applicant.city}"/>
                </label>
                <button type="submit" class="submit">${update}</button>
            </form>
        </div>
        <div class="personal_data">
            <div class="vacancies after-box">${prof_skills}</div>
            <c:forEach items="${sessionScope.applicant.skills}" var="vacancy" varStatus="loop">
                <label>
                    <span></span>
                    <input class="input_data" type="text" name="city" value="${vacancy.title}"/>
                </label>
            </c:forEach>
            <button class="submit" onClick="location.href='Controller?command=take_technologies'">${change}</button>
        </div>

        <c:if test="${sessionScope.applicant.jobVacancy != null}">
            <div>
                <div class="vacancies after-box"></div>
                <div class="personal_data">
                    <div class="vacancies">${application}</div>
                    <div class="applyes">
                        <label>
                            <h3 class="title"
                                onClick="location.href='Controller?command=show_vacancy&item=${sessionScope.applicant.jobVacancy.id}'">${sessionScope.applicant.jobVacancy.jobTitle}</h3>
                        </label>
                        <button class="submit" onclick="location.href='#deleteApplication'">${delete}</button>
                    </div>
                </div>
                <div class="personal_data">
                    <div class="vacancies after-box">${interview}</div>
                    <div class="interview">
                        <c:if test="${sessionScope.applicant.interview.datePreInt != null && sessionScope.applicant.interview.preliminaryInterview =='E'}">
                            <label>
                                <span>${preliminary}</span><br>
                                <span>${date}, ${time}</span><br>
                                    ${sessionScope.applicant.interview.datePreInt} ${sessionScope.applicant.interview.timePreInt}
                            </label>
                        </c:if>

                        <c:if test="${sessionScope.applicant.interview.preliminaryInterview == 'F'}">
                            <label>
                                <span>${pre_results}</span><br>
                                    ${status_f}
                            </label>
                        </c:if>

                        <c:if test="${sessionScope.applicant.interview.preliminaryInterview == 'E' && sessionScope.applicant.interview.datePreInt == null}">
                            <label>
                                <span>${preliminary}</span><br>
                                    ${status_e}
                            </label>
                        </c:if>

                        <c:if test="${sessionScope.applicant.interview.preliminaryInterview == 'S'}">
                            <label>
                                <span>${pre_results}</span><br>
                                    ${status_s}
                            </label>
                        </c:if>

                        <c:if test="${sessionScope.applicant.interview.dateTecInt != null && sessionScope.applicant.interview.technicalInterview =='E'}">
                            <label>
                                <span>${technical}</span><br>
                                <span>${date}, ${time}</span><br>
                                    ${sessionScope.applicant.interview.dateTecInt} ${sessionScope.applicant.interview.timeTecInt}
                            </label>
                        </c:if>

                        <c:if test="${sessionScope.applicant.interview.technicalInterview == 'F'}">
                            <label>
                                <span>${tech_results}</span><br>
                               ${status_f}
                            </label>
                        </c:if>

                        <c:if test="${sessionScope.applicant.interview.technicalInterview == 'E' && sessionScope.applicant.interview.dateTecInt == null}">
                            <label>
                                <span>${technical}</span><br>
                                ${status_e}
                            </label>
                        </c:if>

                        <c:if test="${sessionScope.applicant.interview.technicalInterview == 'S'}">
                            <label>
                                <span>${tech_results}</span><br>
                                ${status_s}
                            </label>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:if>
    </c:when>
    <c:otherwise>
        <form name="update_admin-form" onsubmit="return validateUpdateAdminForm()" method="post" action="Controller">
            <input class="input_data" type="hidden" name="command" value="update_admin"/>
            <label>
                <span>${name}</span>
                <div class="error-message">${nameError}</div>
                <input class="input_data" type="text" name="name" value="${sessionScope.employee.name}"/>
            </label>
            <label>
                <span>${surname}</span>
                <div class="error-message">${surnameError}</div>
                <input class="input_data" type="text" name="surname" value="${sessionScope.employee.surname}"/>
            </label>
            <label>
                <span>${email}</span>
                <div class="error-message">${emailError}</div>
                <input class="input_data" type="email" name="email" value="${sessionScope.employee.email}"/>
            </label>
            <label>
                <span>${phone}</span>
                <div class="error-message">${phoneError}</div>
                <input class="input_data" type="text" name="phone" value="${sessionScope.employee.phoneNumber}"/>
            </label>
            <label>
                <span>workId</span>
                <div class="error-message">${workIdError}</div>
                <input class="input_data" type="text" name="workId" value="${sessionScope.employee.workId}"/>
            </label>
            <button type="submit" class="submit">${update}</button>
        </form>
    </c:otherwise>
</c:choose>
<div id="deleteApplication" class="modalDialog delete_apply">
    <div>
        <a href="#close" title="Закрыть" class="close">X</a>
        <div class="vacancies">${delete_apply}</div>
        <button class="submit" onclick="location.href='Controller?command=delete_application'">${delete}</button>
        <button class="submit" onclick="location.href='#close'">${close}</button>
        </form>

    </div>
</div>
<div id="f" style="clear: both">
    <%@ include file="/WEB-INF/jsp/footer.jsp" %>
</div>
</body>
</html>

