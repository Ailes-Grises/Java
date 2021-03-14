<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ログイン認証</title>
</head>

<body>
	<!-- ログインフォームの作成 -->
	<!-- "conntroller" というアノテーションのサーブレットに処理を飛ばす -->
	<form action="/login_apps/controller" method="post">
	Name : <input type="text" name="username"><br><br>
	Password : <input type="text" name="password"><br><br><br>
	<input type="submit" value="Login">
	</form>
</body>
</html>
