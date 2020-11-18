package webApplication27;

import com.sun.security.auth.login.ConfigFile;
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import webApplication27.*;

public class Control extends HttpServlet {
  /** Handles the HTTP <code>GET</code> method.
   * @param request servlet request
   * @param response servlet response
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    RequestDispatcher rd = null;
    rd = getServletConfig().getServletContext().getRequestDispatcher("/login.jsp");
    rd.forward(request, response);
    //processRequest(request, response);
  }

  /** Handles the HTTP <code>POST</code> method.
   * @param request servlet request
   * @param response servlet response
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    if (request.getParameter("pagename").equals("SEND") == true) {
      webApplication27.UserBean ub = null;
      ub = new webApplication27.UserBean();

      String formun = request.getParameter("username");
      String formps = request.getParameter("password");
      ub.setaName(formun);
      ub.setPassword(formps);

      webApplication27.UserAuthBean uab = null;
      uab = new webApplication27.UserAuthBean();

      HttpSession session = null;
      RequestDispatcher rd = null;
      if (uab.execute(ub) == true) {
        //認証に成功
        //セッションオブジェクトを作成
        if (checkSession(request) == true) {
          //セッションオブジェクトがある場合は獲得
          session = request.getSession(false);
        } else {
          //セッションオブジェクトがない場合は新規作成
          session = request.getSession(true);
        }
        //Beansをセッションオブジェクトに保存
        session.setAttribute("userbeanses", ub);


        rd = getServletConfig().getServletContext().getRequestDispatcher("/authok.jsp");
      } else {
        //認証に失敗
        rd = getServletConfig().getServletContext().getRequestDispatcher("/autherror.jsp");
      }
      rd.forward(request, response);
    } else {
      String[] product = (String[]) request.getParameterValues("product");
      HttpSession session = request.getSession(false);
      webApplication27.UserBean ub
        = (webApplication27.UserBean) session.getAttribute("userbeanses");
      ub.setProduct(product);

      webApplication27.UserOrderBean uob = new webApplication27.UserOrderBean();
      boolean beanResult = uob.execute(ub);

      RequestDispatcher rd = null;
      rd = getServletConfig().getServletContext().getRequestDispatcher("/order.jsp");
      rd.forward(request, response);
    }
  }

  /** Returns a short description of the servlet. */
  public String getServletInfo() {
    return "Short description";
  }

  //セッションオブジェクトのチェックメソッド
  public boolean checkSession(HttpServletRequest req) {
    HttpSession session = req.getSession(false);
    if (session != null) {
      return true;
    } else {
      return false;
    }
  }
}
