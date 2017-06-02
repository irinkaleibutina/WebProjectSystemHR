<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8">--%>
    <%--<title>DreamJob</title>--%>

    <%--<fmt:setLocale value="${sessionScope.locale}"/>--%>
    <%--<fmt:setBundle basename="resource.locale" var="loc"/>--%>
    <%--<fmt:message bundle="${loc}" key="locale.email" var="email"/>--%>
    <%--<fmt:message bundle="${loc}" key="locale.password" var="password"/>--%>
    <%--<fmt:message bundle="${loc}" key="locale.login" var="login"/>--%>
    <%--<fmt:message bundle="${loc}" key="locale.name" var="name"/>--%>
    <%--<fmt:message bundle="${loc}" key="locale.signIn" var="signIn"/>--%>
    <%--<fmt:message bundle="${loc}" key="locale.signUp" var="signUp"/>--%>
    <%--<fmt:message bundle="${loc}" key="locale.surname" var="surname"/>--%>
    <%--<fmt:message bundle="${loc}" key="locale.welcome" var="welcome"/>--%>
    <%--<fmt:message bundle="${loc}" key="locale.mesSignIn" var="mesSignIn"/>--%>
    <%--<fmt:message bundle="${loc}" key="locale.mesSignUp" var="mesSignUp"/>--%>
    <%--<fmt:message bundle="${loc}" key="locale.newUser" var="newUser"/>--%>
    <%--<fmt:message bundle="${loc}" key="locale.registeredUser" var="registeredUser"/>--%>

    <%--<fmt:message bundle="${loc}" key="locale.passerr" var="passerr"/>--%>

    <%--<link rel="stylesheet" href="css/signin.css">--%>
    <%--<link rel="stylesheet" href="css/main.css">--%>
    <%--<link rel="stylesheet" href="css/modal_window_registration.css">--%>
    <%--<head>--%>

<%--<body>--%>
<%--<jsp:include page="header.jsp"></jsp:include>--%>


<%--<div class="cont">--%>
    <%--<form name="login-form" onsubmit="return validateLoginForm()" method="post" action="Controller">--%>
        <%--<input class="input_data" type="hidden" name="command" value="login">--%>
        <%--<div class="forms sign-in">--%>
            <%--<h2>${welcome}</h2>--%>
            <%--<h3>${requestScope.get("fail")}</h3>--%>
            <%--<label>--%>
                <%--<span>${login}</span>--%>
                <%--<div class="error-message">asas</div>--%>
                <%--<input class="input_data" type="text" name="login"/>--%>
            <%--</label>--%>


            <%--<label>--%>
                <%--<span>${password}</span>--%>
                <%--<div class="error-message">${passerr}</div>--%>
                <%--<input class="input_data" type="password" name="password"/>--%>
            <%--</label>--%>

            <%--<button type="submit" class="submit">${signIn}</button type="submit">--%>
        <%--</div>--%>
    <%--</form>--%>


    <%--<div class="sub-cont">--%>
        <%--<div class="img">--%>

            <%--<div class="img__text m--up">--%>
                <%--<h2>${newUser}</h2>--%>
                <%--<p>${mesSignUp}</p>--%>
            <%--</div>--%>
            <%--<div class="img__text m--in">--%>
                <%--<h2>${registeredUser}</h2>--%>
                <%--<p>${mesSignIn}</p>--%>
            <%--</div>--%>

            <%--<div class="img__btn">--%>
                <%--<span class="m--up">${signUp}</span>--%>
                <%--<span class="m--in">${signIn}</span>--%>
            <%--</div>--%>
        <%--</div>--%>


        <%--<form name="reg-form" onsubmit="return validateRegistrationForm()" method="post" action="Controller">--%>
            <%--<input  class="input_data" type="hidden" name="command" value="registration"/>--%>
            <%--<div class="forms sign-up">--%>
                <%--<h2>${welcome}</h2>--%>
                <%--<label>--%>
                    <%--<span>${name}</span>--%>
                    <%--<div class="error-message">${nameerr}</div>--%>
                    <%--<input class="input_data" type="text" name="name"/>--%>
                <%--</label>--%>

                <%--<label>--%>
                    <%--<span>${surname}</span>--%>
                    <%--<div class="error-message">${passerr}</div>--%>
                    <%--<input class="input_data" type="text" name="surname"/>--%>
                <%--</label>--%>

                <%--<label>--%>
                    <%--<span>${email}</span>--%>
                    <%--<div class="error-message">${passerr}</div>--%>
                    <%--<input class="input_data" type="email" name="email"/>--%>
                <%--</label>--%>

                <%--<label>--%>
                    <%--<span>${login}</span>--%>
                    <%--<div class="error-message">${passerr}</div>--%>
                    <%--<input class="input_data" type="text" name="login"/>--%>
                <%--</label>--%>

                <%--<label>--%>
                    <%--<span>${password}</span>--%>
                    <%--<div class="error-message">${passerr}</div>--%>
                    <%--<input class="input_data" type="password" name="password"/>--%>
                <%--</label>--%>

                <%--<button type="submit" class="submit">${signUp}</button>--%>
            <%--</div>--%>
        <%--</form>--%>
    <%--</div>--%>
<%--</div>--%>


<%--<a class="close" title="Закрыть" href="#close"></a>--%>
<%--</div>--%>

<%--<script type="text/javascript">--%>
    <%--document.querySelector('.img__btn').addEventListener('click', function () {--%>
        <%--document.querySelector('.cont').classList.toggle('s--signup');--%>
    <%--});--%>


<%--//    function validateLoginForm() {--%>
<%--//        var form = document.forms["login-form"];--%>
<%--//--%>
<%--//        var login = form["login"];--%>
<%--//        var password = form["password"];--%>
<%--//--%>
<%--//        var loginRegEx = /^[a-zA-Z]+([-\'][a-zA-Z]+)*$/;--%>
<%--//        var passwordRegEx = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,25}$/;--%>
<%--//--%>
<%--//        var validationCheck = new Array();--%>
<%--//--%>
<%--//        validationCheck.push(validateUsername(login, loginRegEx));--%>
<%--//        validationCheck.push(validateWithRegExp(password, passwordRegEx));--%>
<%--//--%>
<%--//        if (validationCheck.includes(false)) {--%>
<%--//            return false;--%>
<%--//        } else {--%>
<%--//            return true;--%>
<%--//        }--%>
<%--//    }--%>
<%--//--%>
<%--//    function validateRegistrationForm() {--%>
<%--//        var form = document.forms["reg-form"];--%>
<%--//--%>
<%--//        var name = form["name"];--%>
<%--//        var surname = form["surname"];--%>
<%--//        var email = form["email"];--%>
<%--//        var password = form["password"];--%>
<%--//        var login = form["login"];--%>
<%--//--%>
<%--//--%>
<%--//        var nameRegEx = /^[a-zA-Z]+([-\'][a-zA-Z]+)*$/;--%>
<%--//        var surnameRegEx = /^[a-zA-Z]+([-\'\s][a-zA-Z]+)*$/;--%>
<%--//        var emailRegEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;--%>
<%--//        var passwordRegEx = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,25}$/;--%>
<%--//        var loginRegEx = /^(?=.*\d)[a-zA-Z]+([0-9a-zA-Z-_\\.,;:\s]){14,}$/;--%>
<%--//--%>
<%--//        var validationCheck = new Array();--%>
<%--//--%>
<%--//        validationCheck.push(validateUsername(name, nameRegEx));--%>
<%--//        validationCheck.push(validateUsername(surname, surnameRegEx));--%>
<%--//        validationCheck.push(validateWithRegExp(email, emailRegEx));--%>
<%--//        validationCheck.push(validateWithRegExp(password, passwordRegEx));--%>
<%--//        validationCheck.push(validateWithRegExp(login, loginRegEx));--%>
<%--//--%>
<%--//--%>
<%--//        if (validationCheck.includes(false)) {--%>
<%--//            return false;--%>
<%--//        } else {--%>
<%--//            return true;--%>
<%--//        }--%>
<%--//    }--%>

    <%--function validateUsername(username, usernameRegEx) {--%>
        <%--if ((username.value.match(usernameRegEx) == null) || (username.value.length < 5) || (username.value.length > 30)) {--%>
            <%--makeInvalid(username)--%>
            <%--return false;--%>
        <%--} else {--%>
            <%--makeValid(username);--%>
            <%--return true;--%>
        <%--}--%>
    <%--}--%>

    <%--function makeInvalid(element) {--%>
        <%--element.className = "invalid";--%>
        <%--element.parentNode.getElementsByClassName("error-message")[0].style.display = "block";--%>
    <%--}--%>

    <%--function makeValid(element) {--%>
        <%--if (element.className == "invalid") element.className = "";--%>
        <%--element.parentNode.getElementsByClassName("error-message")[0].style.display = "none";--%>
    <%--}--%>


    <%--function validateWithRegExp(element, regExp) {--%>
        <%--if (element.value.match(regExp) == null) {--%>
            <%--makeInvalid(element);--%>
            <%--return false;--%>
        <%--} else {--%>
            <%--makeValid(element);--%>
            <%--return true;--%>
        <%--}--%>
    <%--}--%>

<%--</script>--%>
<%--</body>--%>
<%--</html>--%>


