<%@ page language="java" contentType="text/html" import="model.user_info" pageEncoding="UTF-8" %>
<%
// サーブレット内のフォワードで設定しておいた名前を呼び出す
user_info UserInfo = (user_info)request.getAttribute("UserInfo");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ようこそ，<%= UserInfo.getName() %>さん</title>
	</head>

	<body>
		認証に成功しました．(パスワードは<%= UserInfo.getPassword() %>ですw)
	</body>
</html>
