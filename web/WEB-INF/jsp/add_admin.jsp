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
    <fmt:message bundle="${loc}" key="locale.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.password" var="password"/>
    <fmt:message bundle="${loc}" key="locale.login" var="login"/>
    <fmt:message bundle="${loc}" key="locale.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.signIn" var="signIn"/>
    <fmt:message bundle="${loc}" key="locale.signUp" var="signUp"/>
    <fmt:message bundle="${loc}" key="locale.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="locale.welcome" var="welcome"/>
    <fmt:message bundle="${loc}" key="locale.mesSignIn" var="mesSignIn"/>
    <fmt:message bundle="${loc}" key="locale.mesSignUp" var="mesSignUp"/>
    <fmt:message bundle="${loc}" key="locale.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="locale.newUser" var="newUser"/>
    <fmt:message bundle="${loc}" key="locale.registeredUser" var="registeredUser"/>
    <fmt:message bundle="${loc}" key="locale.nameerror" var="nameError"/>
    <fmt:message bundle="${loc}" key="locale.surnameerror" var="surnameError"/>
    <fmt:message bundle="${loc}" key="locale.loginerror" var="loginError"/>
    <fmt:message bundle="${loc}" key="locale.passerror" var="passwordError"/>
    <fmt:message bundle="${loc}" key="locale.emailerror" var="emailError"/>
    <fmt:message bundle="${loc}" key="locale.phoneerror" var="phoneError"/>
    <fmt:message bundle="${loc}" key="locale.workIdError" var="workIdError"/>

    <link rel="stylesheet" href="css/signin.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<form name="add_admin-form" onsubmit="return validateAdminForm()" method="post" action="Controller">
    <input class="input_data" type="hidden" name="command" value="add_admin"/>

    <div class="regform">
        <label>
            <span>${name}</span>
            <div class="error-message">${nameError}</div>
            <input class="input_data" type="text" name="name"/>
        </label>
        <label>
            <span>${surname}</span>
            <div class="error-message">${surnameError}</div>
            <input class="input_data" type="text" name="surname"/>
        </label>
        <label>
            <span>${email}</span>
            <div class="error-message">${emailError}</div>
            <input class="input_data" type="email" name="email"/>
        </label>
        <label>
            <span>${login}</span>
            <div class="error-message">${loginError}</div>
            <input class="input_data" type="text" name="login"/>
        </label>
        <label>
            <span>${password}</span>
            <div class="error-message">${passwordError}</div>
            <input class="input_data" type="password" name="password"/>
        </label>

        <label>
            <span>workId</span>
            <div class="error-message">${workIdError}</div>
            <input class="input_data" type="text" name="workId"/>
        </label>

        <label>
            <span>${phone}</span>
            <div class="error-message">${phoneError}</div>
            <input class="input_data" type="text" name="phone"/>
        </label>
        <button type="submit" class="submit">${signUp}</button>
    </div>
</form>
</body>
</html>

