package webApplication27;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class OrderHandler extends TagSupport {
  public int doEndTag() {
    HttpSession session = pageContext.getSession();
    //String[] product = (String[])session.getAttribute("userbeanses");
    webApplication27.UserBean ub
      = (webApplication27.UserBean) session.getAttribute("userbeanses");
    String[] product = ub.getProduct();

    JspWriter out = pageContext.getOut();

    try {
      out.println("<TR><TD>Name</TD></TR>");

      for (int i = 0; i < product.length; i++) {
        out.print("<TR><TD>");
        out.print(product[i]);
        out.print("</TD></TR>");
      }
    } catch (Exception e) {
    }
    return EVAL_PAGE;
  }

  /** Creates a new instance of OrderHandler */
  public OrderHandler() {
  }
}
