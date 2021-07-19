<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="userbeanses" scope="session" class="webApplication27.UserBean" />
<%@ taglib uri="/OrderLibrary" prefix="product" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Book online</title>
  </head>
  <body>
    <h1>注文</h1>
    <TABLE align="center" border="1" width="100">
      <CAPTION align="TOP">
        <jsp:getProperty name="userbeanses" property="aName" /> さんの注文リスト
      </CAPTION>
      <product:getOrder />
    </TABLE>
    <BR><BR>
    <A HREF="./Control">ログインに戻る</A>
  </body>
</html>
