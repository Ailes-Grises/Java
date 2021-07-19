<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="userbeanses" scope="session" class="webApplication27.UserBean" />
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <h1>Login</h1>
    <p><jsp:getProperty name="userbeanses" property="aName" /> さん、ようこそ</p>
    <!--
    <FORM>
        <INPUT TYPE="submit" name="pagename" value="商品一覧を見る">
    </FORM>
    -->
    <a href="product.jsp">商品一覧</a>
  </body>
</html>
