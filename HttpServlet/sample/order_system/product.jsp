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
    <h1>JSP Page</h1>
    <P><jsp:getProperty name="userbeanses" property="aName" /> さん！商品を選んでね</P>
    <FORM method="POST" action="Control">
      <INPUT TYPE="checkbox" name="product" value="JustTomcat">JustTomcat<BR>
      <INPUT TYPE="checkbox" name="product" value="WebService">WebService<BR>
      <INPUT TYPE="checkbox" name="product" value="StepJava">StepJava<BR>
      <INPUT TYPE="checkbox" name="product" value="Servlet">Servlet<BR>
      <INPUT TYPE="checkbox" name="product" value="JSP">JSP<BR>
      <BR><BR>
      <INPUT TYPE="submit" name="pagename" value="注文">
      <INPUT TYPE="reset" value="リセット">
    </FORM>
  </body>
</html>
