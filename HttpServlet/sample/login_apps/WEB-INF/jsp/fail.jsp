<%@ page language="java" contentType="text/html" import="model.user_info" pageEncoding="UTF-8" %>
<%
user_info UserInfo = (user_info)request.getAttribute("UserInfo");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>認証に失敗しました</title>
	</head>

	<body>
		認証に失敗しました．再ログインして下さい．<br>
		名前 : <%= UserInfo.getName() %><br>
		パスワード : <%= UserInfo.getPassword() %><br><br>

		<form action="/login_apps/controller" type="post">
			Name : <input name="username" type="text"><br>
			Password : <input name="password" type="text"><br><br>
			<input type="submit" value="Login">
		</form>
	</body>
</html>
