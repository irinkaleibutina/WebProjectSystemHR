<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>SystemHR</title></head>
<body>

<form action="Controller" method="post">
 <input type="hidden" name="command" value="sign_in"/>
  Введите логин:<br/>
  <input type="text" name="login"/><br/>
  Введите пароль:<br/>
  <input type="password" name="password"/><br/>
  <input type="submit" value="Войти"/>
</form>

<form action="Controller" method="post">
  <input type="hidden" name="command" value="sign_up"/>
  <input type="submit" value="Регистрация"
      onclick= href="Controller?action=redirect&page=/WEB-INF/jsp/signUp.jsp">hhjh/>


</form>

</body>
</html>


