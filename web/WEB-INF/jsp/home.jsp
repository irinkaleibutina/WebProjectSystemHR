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
    <fmt:message bundle="${loc}" key="locale.copyright" var="copyright"/>

    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div class="slideshow-container">
    <div class="mySlides fade">
        <img src="img7.jpg" width=100% height="660">
    </div>
    <div class="mySlides fade">
        <img src="img6.jpg" width=100% height="660">
    </div>
    <div class="mySlides fade">
        <img src="img8.jpg" width=100% height="660">
    </div>
</div>
<br>
<div style="text-align:center">
    <span class="dot"></span>
    <span class="dot"></span>
    <span class="dot"></span>
</div>
<div class="vacc">
    <%@ include file="/WEB-INF/jsp/menuItem.jsp" %>
</div>
<div class="newss" style="clear: both">
    <%@ include file="/WEB-INF/jsp/news.jsp" %>
</div>
<div id="f1" style="clear: both">
    <nav>
        <ul class="nav">
            <li><a href="Controller?command=main_page" class="logo">DreamJob</a></li>
            <li class="copyright">©${copyright}</li>
        </ul>
    </nav>
</div>
<script>
    var slideIndex = 0;
    showSlides();

    function showSlides() {
        var i;
        var slides = document.getElementsByClassName("mySlides");
        var dots = document.getElementsByClassName("dot");
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slideIndex++;
        if (slideIndex > slides.length) {
            slideIndex = 1
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " active";
        setTimeout(showSlides, 5000);
    }
</script>
</body>
</html>

