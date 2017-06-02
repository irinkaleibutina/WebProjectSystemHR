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
    <fmt:message bundle="${loc}" key="locale.signOut" var="signOut"/>
    <fmt:message bundle="${loc}" key="locale.add" var="add"/>
    <fmt:message bundle="${loc}" key="locale.delete" var="delete"/>
    <fmt:message bundle="${loc}" key="locale.applicants" var="applicants"/>
    <fmt:message bundle="${loc}" key="locale.admins" var="admins"/>
    <fmt:message bundle="${loc}" key="locale.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.password" var="password"/>
    <fmt:message bundle="${loc}" key="locale.login" var="login"/>
    <fmt:message bundle="${loc}" key="locale.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="locale.signIn" var="signIn"/>
    <fmt:message bundle="${loc}" key="locale.signUp" var="signUp"/>
    <fmt:message bundle="${loc}" key="locale.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="locale.welcome" var="welcome"/>
    <fmt:message bundle="${loc}" key="locale.mesSignIn" var="mesSignIn"/>
    <fmt:message bundle="${loc}" key="locale.mesSignUp" var="mesSignUp"/>
    <fmt:message bundle="${loc}" key="locale.newUser" var="newUser"/>
    <fmt:message bundle="${loc}" key="locale.registeredUser" var="registeredUser"/>
    <fmt:message bundle="${loc}" key="locale.nameerror" var="nameError"/>
    <fmt:message bundle="${loc}" key="locale.surnameerror" var="surnameError"/>
    <fmt:message bundle="${loc}" key="locale.loginerror" var="loginError"/>
    <fmt:message bundle="${loc}" key="locale.passerror" var="passwordError"/>
    <fmt:message bundle="${loc}" key="locale.emailerror" var="emailError"/>
    <fmt:message bundle="${loc}" key="locale.phoneerror" var="phoneError"/>
    <fmt:message bundle="${loc}" key="locale.workIdError" var="workIdError"/>
    <fmt:message bundle="${loc}" key="locale.copyright" var="copyright"/>
    <fmt:message bundle="${loc}" key="locale.titleerror" var="titleError"/>
    <fmt:message bundle="${loc}" key="locale.descriptionerror" var="descriptionError"/>
    <fmt:message bundle="${loc}" key="locale.title" var="title"/>
    <fmt:message bundle="${loc}" key="locale.description" var="description"/>
    <fmt:message bundle="${loc}" key="locale.city" var="city"/>
    <fmt:message bundle="${loc}" key="locale.country" var="country"/>
    <fmt:message bundle="${loc}" key="locale.clear" var="clear"/>
    <fmt:message bundle="${loc}" key="locale.titleerror" var="titleError"/>
    <fmt:message bundle="${loc}" key="locale.descriptionerror" var="descriptionError"/>
    <fmt:message bundle="${loc}" key="locale.countryerror" var="countryError"/>
    <fmt:message bundle="${loc}" key="locale.cityerror" var="cityError"/>
    <fmt:message bundle="${loc}" key="locale.close" var="close"/>
    <fmt:message bundle="${loc}" key="locale.find" var="find"/>

    <link rel="stylesheet" href="css/signin.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/modal_window.css">
</head>
<body>
<header class="siteHeader">
    <nav>
        <ul class="nav">
            <li><a href="Controller?command=main_page" class="logo">DreamJob</a></li>
            <c:choose>
                <c:when test="${sessionScope.employee != null}">
                    <li><a href="Controller?command=show_vacancies" class="menu">${vacancies}</a>
                        <ul>
                            <li><a href=#openModal class="menu">${add}</a></li>
                            <li><a href="#search_vacancy" class="menu">${search}</a></li>
                        </ul>
                    </li>
                    <li><a href="Controller?command=show_applicants" class="menu">${applicants}</a>
                        <ul>
                            <li>
                                <a href="#search_applicant" class="menu">${search}</a>
                            </li>
                        </ul>
                    </li>
                    <li><a href="Controller?command=show_admins" class="menu">${admins}</a>
                        <ul>
                            <li><a href="#add_admin" class="menu">${add}</a></li>
                        </ul>
                    </li>
                    <li><a href="#" class="menu">Content</a>
                        <ul>
                            <li><a href="Controller?command=take_news" class="menu">${news}</a>
                                <ul>
                                    <li><a href="#news_window" class="menu">${add}</a></li>
                                    <li><a href="#news_search" class="menu">${search}</a></li>
                                </ul>
                            </li>
                            <li><a href="#" class="menu">${about}</a>
                                <ul>
                                    <li><a href="#" class="menu">${add}</a></li>
                                    <li><a href="#" class="menu">${delete}</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>

                    <!-- <li><a href="#">${about}</a>
                    <ul>
                    <li><a href="Controller?command=show&item=company">${company}</a></li>
                    <li><a href="Controller?command=show&item=employees">${employees}</a></li>
                    </ul>
                    </li> -->

                    <li class="form"><a href="Controller?command=sign_out" class="menu">${signOut}</a></li>
                    <li class="form"><a
                            href="Controller?command=redirect&page=profile.jsp"
                            class="menu">${sessionScope.employee.name} ${sessionScope.employee.surname}</a>
                    </li>
                </c:when>
                <c:when test="${sessionScope.applicant != null}">
                    <li><a href="Controller?command=show_vacancies" class="menu">${vacancies}</a></li>
                    <li><a href="Controller?command=take_news" class="menu">${news}</a></li>
                    <li><a href="#" class="menu">${about}</a>
                        <ul>
                            <li><a href="Controller?command=show_company" class="menu">${company}</a></li>
                            <li><a href="Controller?command=show_employees" class="menu">${employees}</a></li>
                        </ul>
                    </li>
                    <li class="form"><a href="Controller?command=sign_out" class="menu">${signOut}</a></li>
                    <li class="form"><a
                            href="Controller?command=get_applicant"
                            class="menu">${sessionScope.applicant.name} ${sessionScope.applicant.surname}</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="Controller?command=show_vacancies" class="menu">${vacancies}</a></li>
                    <li><a href="Controller?command=take_news" class="menu">${news}</a></li>
                    <li><a href="#" class="menu">${about}</a>
                        <ul>
                            <li><a href="Controller?command=show_company" class="menu">${company}</a></li>
                            <li><a href="Controller?command=hhhhhh" class="menu">${employees}</a></li>
                        </ul>
                    </li>
                    <li class="form"><a href="#openRegForm" class="menu">${signIn}</a></li>
                    <li class="form"><a href="#" class="menu">${lang}</a>
                        <ul>
                            <li><a href="Controller?command=change_locale&lang=eng" class="menu">
                                <div class="locale eng">${eng}</div>
                            </a></li>
                            <li><a href="Controller?command=change_locale&lang=ru" class="menu">
                                <div class="locale ru">${ru}</div>
                            </a></li>
                        </ul>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</header>

<%--Add vacancy--%>

<div id="openModal" class="modalDialog add_vacancy">
    <div>
        <a href="#close" title="Закрыть" class="close">X</a>
        <form name="add_vacancy-form" onsubmit="return validateVacancyForm()" method="post" action="Controller">
            <input class="input_data" type="hidden" name="command" value="add_vacancy"/>

            <label>
                <span>${title}</span>
                <div class="error-message">${titleError}</div>
                <input class="input_data" type="text" name="title"/>
            </label>

            <label>
                <span>${description}</span>
                <div class="error-message">${descriptionError}</div>
                <textarea name="description" cols="40" rows="5"></textarea>
            </label>

            <label>
                <span>${country}</span>
                <div class="error-message">${countryError}</div>
                <input class="input_data" type="text" name="country"/>
            </label>

            <label>
                <span>${city}</span>
                <div class="error-message">${cityError}</div>
                <input class="input_data" type="text" name="city"/>
            </label>

            <button class="submit" type="submit">${add}</button>
            <button type="reset" class="submit">${clear}</button>
        </form>
    </div>
</div>


<div class="cont" id="openRegForm">
    <div>
        <a href="#close" title="Закрыть" class="close">X</a>
        <form name="login-form" onsubmit="return validateLoginForm()" method="post" action="Controller">
            <input type="hidden" name="command" value="login">
            <div class="forms sign-in">
                <h2>${welcome}</h2>
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
                <button type="submit" class="submit">${signIn}</button>
            </div>
        </form>

        <div class="sub-cont">
            <div class="img">
                <div class="img__text m--up">
                    <h2>${newUser}</h2>
                    <p>${mesSignUp}</p>
                </div>
                <div class="img__text m--in">
                    <h2>${registeredUser}</h2>
                    <p>${mesSignIn}</p>
                </div>

                <div class="img__btn">
                    <span class="m--up">${signUp}</span>
                    <span class="m--in">${signIn}</span>
                </div>
            </div>

            <form name="reg-form" onsubmit="return validateRegistrationForm()" method="post" action="Controller">
                <input class="input_data" type="hidden" name="command" value="registration"/>
                <div class="forms sign-up">
                    <h2>${welcome}</h2>
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
                        <span>${phone}</span>
                        <div class="error-message">${phoneError}</div>
                        <input class="input_data" type="text" name="phone"/>
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
                    <button type="submit" class="submit">${signUp}</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--Add news--%>

<div id="news_window" class="modalDialog add_new">
    <div>
        <a href="#close" title="Закрыть" class="close">X</a>
        <form name="add_news_form" onsubmit="return validateNewsForm()" method="post" action="Controller">
            <input class="input_data" type="hidden" name="command" value="add_news"/>
            <label>
                <span>${title}</span>
                <div class="error-message">${titleError}</div>
                <input class="input_data" type="text" name="title"/>
            </label>

            <label>
                <span>${description}</span>
                <div class="error-message">${descriptionError}</div>
                <textarea name="description" cols="40" rows="5"></textarea>
            </label>
            <button class="submit" type="submit">${add}</button>
            <button type="reset" class="submit">${clear}</button>
        </form>
    </div>
</div>

<%--Search news--%>

<div id="news_search" class="modalDialog search_news">
    <div>
        <a href="#close" title="Закрыть" class="close">X</a>
        <form name="search_news-form" onsubmit="return validateSearchNewsForm()" method="post" action="Controller">
            <input class="input_data" type="hidden" name="command" value="search_news"/>

            <label>
                <span>${title}</span>
                <div class="error-message">${titleError}</div>
                <input class="input_data" type="text" name="title"/>
            </label>
            <button class="submit" type="submit">${find}</button>
            <button type="reset" class="submit">${clear}</button>
        </form>

    </div>
</div>

<%--Search vacancy--%>

<div id="search_vacancy" class="modalDialog search_vac">
    <div>
        <a href="#close" title="Закрыть" class="close">X</a>
        <form name="search_job-form" onsubmit="return validateSearchJobForm()" method="post" action="Controller">
            <input class="input_data" type="hidden" name="command" value="search_vacancy"/>

            <label>
                <span>${title}</span>
                <div class="error-message">${titleError}</div>
                <input class="input_data" type="text" name="title"/>
            </label>
            <button class="submit" type="submit">${find}</button>
            <button type="reset" class="submit">${clear}</button>
        </form>
    </div>
</div>

<%--Search applicant--%>

<div id="search_applicant" class="modalDialog search_appl">
    <div>
        <a href="#close" title="Закрыть" class="close">X</a>
        <form name="search_applicant-form" onsubmit="return validateSearchApplForm()" method="post" action="Controller">
            <input class="input_data" type="hidden" name="command" value="search_applicant"/>
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
            <button class="submit" type="submit">${search}</button>
            <button type="reset" class="submit">${clear}</button>
        </form>
    </div>
</div>

<%--Add admin--%>

<div id="add_admin" class="modalDialog add_admin">
    <div>
        <a href="#close" title="Закрыть" class="close">X</a>
        <jsp:include page="add_admin.jsp"></jsp:include>
    </div>
</div>


<script type="text/javascript">
    document.querySelector('.img__btn').addEventListener('click', function () {
        document.querySelector('.cont').classList.toggle('s--signup');
    });

    function validateLoginForm() {
        var form = document.forms["login-form"];

        var login = form["login"];
        var password = form["password"];

        var passwordRegEx = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,25}$/;
        var loginRegEx = /^[\w._-]{6,}$/;

        var validationCheck = new Array();

        validationCheck.push(validateUsername(login, loginRegEx));
        validationCheck.push(validateWithRegExp(password, passwordRegEx));

        if (validationCheck.includes(false)) {
            return false;
        } else {
            return true;
        }
    }

    function validateRegistrationForm() {
        var form = document.forms["reg-form"];

        var name = form["name"];
        var surname = form["surname"];
        var email = form["email"];
        var password = form["password"];
        var login = form["login"];
        var phone = form["phone"];

        var nameRegEx = /^[a-zA-Zа-яА-Я]+([-\'][а-яА-Яa-zA-Z]+)*$/;
        var surnameRegEx = /^[а-яА-Яa-zA-Z]+([-\'\s][а-яА-Яa-zA-Z]+)*$/;
        var emailRegEx = /^[a-zA-Z0-9_.]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$/
        var passwordRegEx = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,25}$/;
        var loginRegEx = /^[\w._-]{6,}$/;
        var phoneRegEx = /\+[0-9]{12}$/;

        var validationCheck = new Array();

        validationCheck.push(validateUsername(name, nameRegEx));
        validationCheck.push(validateUsername(surname, surnameRegEx));
        validationCheck.push(validateWithRegExp(email, emailRegEx));
        validationCheck.push(validateWithRegExp(password, passwordRegEx));
        validationCheck.push(validateWithRegExp(login, loginRegEx));
        validationCheck.push(validateWithRegExp(phone, phoneRegEx));


        if (validationCheck.includes(false)) {
            return false;
        } else {
            return true;
        }
    }

    function validateVacancyForm() {
        var form = document.forms["add_vacancy-form"];

        var title = form["title"];
        var description = form["description"];
        var country = form["country"];
        var city = form["city"];

        var countryRegEx = /^[а-яa-zА-ЯA-Z.-]{2,}$/;
        var cityRegEx = /^[а-яa-zА-ЯA-Z.-]{2,}$/;

        var validationCheck = new Array();

        validationCheck.push(validateWithRegExp(country, countryRegEx));
        validationCheck.push(validateWithRegExp(city, cityRegEx));
        validationCheck.push(validateVacancyParams(description));
        validationCheck.push(validateVacancyParams(title));


        if (validationCheck.includes(false)) {
            return false;
        } else {
            return true;
        }
    }

    function validateSearchJobForm() {
        var form = document.forms["search_job-form"];

        var title = form["title"];

        var validationCheck = new Array();

        validationCheck.push(validateVacancyParams(title));

        if (validationCheck.includes(false)) {
            return false;
        } else {
            return true;
        }
    }

    function validateSearchNewsForm() {
        var form = document.forms["search_news-form"];

        var title = form["title"];

        var validationCheck = new Array();

        validationCheck.push(validateVacancyParams(title));

        if (validationCheck.includes(false)) {
            return false;
        } else {
            return true;
        }
    }

    function validateNewsForm() {
        var form = document.forms["add_news_form"];

        var title = form["title"];
        var description = form["description"];

        var validationCheck = new Array();

        validationCheck.push(validateVacancyParams(title));
        validationCheck.push(validateVacancyParams(description));

        if (validationCheck.includes(false)) {
            return false;
        } else {
            return true;
        }
    }

    function validateSearchApplForm() {

        var form = document.forms["search_applicant-form"];

        var name = form["name"];
        var surname = form["surname"];

        var nameRegEx = /^[a-zA-Zа-яА-Я]+([-\'][а-яА-Яa-zA-Z]+)*$/;
        var surnameRegEx = /^[а-яА-Яa-zA-Z]+([-\'\s][а-яА-Яa-zA-Z]+)*$/;

        var validationCheck = new Array();

        validationCheck.push(validateUsername(name, nameRegEx));
        validationCheck.push(validateUsername(surname, surnameRegEx));

        if (validationCheck.includes(false)) {
            return false;
        } else {
            return true;
        }
    }

    function validateAdminForm() {
        var form = document.forms["add_admin-form"];

        var name = form["name"];
        var surname = form["surname"];
        var email = form["email"];
        var password = form["password"];
        var login = form["login"];
        var phone = form["phone"];
        var workId = form["workId"];

        var nameRegEx = /^[a-zA-Zа-яА-Я]+([-\'][а-яА-Яa-zA-Z]+)*$/;
        var surnameRegEx = /^[а-яА-Яa-zA-Z]+([-\'\s][а-яА-Яa-zA-Z]+)*$/;
        var emailRegEx = /^[a-zA-Z0-9_.]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$/
        var passwordRegEx = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,25}$/;
        var loginRegEx = /^[\w._-]{6,}$/;
        var phoneRegEx = /\+[0-9]{12}$/;
        var workIdRegEx = /^\d{5,}$/

        var validationCheck = new Array();

        validationCheck.push(validateUsername(name, nameRegEx));
        validationCheck.push(validateUsername(surname, surnameRegEx));
        validationCheck.push(validateWithRegExp(email, emailRegEx));
        validationCheck.push(validateWithRegExp(password, passwordRegEx));
        validationCheck.push(validateWithRegExp(login, loginRegEx));
        validationCheck.push(validateWithRegExp(phone, phoneRegEx));

        validationCheck.push(validateWithRegExp(workId, workIdRegEx));

        if (validationCheck.includes(false)) {
            return false;
        } else {
            return true;
        }
    }

    function validateUpdateAdminForm() {
        var form = document.forms["update_admin-form"];

        var name = form["name"];
        var surname = form["surname"];
        var email = form["email"];
        var phone = form["phone"];
        var workId = form["workId"];

        var nameRegEx = /^[a-zA-Zа-яА-Я]+([-\'][а-яА-Яa-zA-Z]+)*$/;
        var surnameRegEx = /^[а-яА-Яa-zA-Z]+([-\'\s][а-яА-Яa-zA-Z]+)*$/;
        var emailRegEx = /^[a-zA-Z0-9_.]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$/
        var phoneRegEx = /\+[0-9]{12}$/;
        var workIdRegEx = /^\d{5,}$/

        var validationCheck = new Array();

        validationCheck.push(validateUsername(name, nameRegEx));
        validationCheck.push(validateUsername(surname, surnameRegEx));
        validationCheck.push(validateWithRegExp(email, emailRegEx));
        validationCheck.push(validateWithRegExp(phone, phoneRegEx));
        validationCheck.push(validateWithRegExp(workId, workIdRegEx));
        if (validationCheck.includes(false)) {
            return false;
        } else {
            return true;
        }
    }
    function validateUsername(username, usernameRegEx) {
        if ((username.value.match(usernameRegEx) == null) || (username.value.length < 1) || (username.value.length > 30)) {
            makeInvalid(username)
            return false;
        } else {
            makeValid(username);
            return true;
        }
    }
    function makeInvalid(element) {
        element.className = "invalid";
        element.parentNode.getElementsByClassName("error-message")[0].style.display = "block";
    }
    function makeValid(element) {
        if (element.className == "invalid") element.className = "";
        element.parentNode.getElementsByClassName("error-message")[0].style.display = "none";
    }
    function validateWithRegExp(element, regExp) {
        if (element.value.match(regExp) == null) {
            makeInvalid(element);
            return false;
        } else {
            makeValid(element);
            return true;
        }
    }
    function validateVacancyParams(parametr) {
        if ((parametr.value.length < 5) || parametr.value.length > 45) {
            makeInvalid(parametr)
            return false;
        } else {
            makeValid(parametr);
            return true;
        }
    }
</script>
</body>
</html>

