<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Insert title here</title></head>
<body>

<form action="Controller" method="post">
 <input type="hidden" name="command" value="sign_in"/>
  Введите логин:<br/>
  <input type="text" name="login" value=""/><br/>
  Введите пароль:<br/>
  <input type="text" name="password" value=""/><br/>
  <input type="submit" value="Войти"/>
</form>

<form action="SignUp" method="post">
  <input type="submit" value="Регистрация"/>
</form>

</body>
</html>


