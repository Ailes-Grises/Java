package servlet;
import model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/controller")
public class controller extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// リクエストパラメータをBeans(user_info クラス) に格納
		user_info UserInfo = new user_info();
		UserInfo.setName(username);
		UserInfo.setPassword(password);

		// 名前とパスワードを認証
		check_userInfo checker = new check_userInfo();

		// 認証情報が間違っていた場合
		if(checker.check(UserInfo) == false){

			// リクエストスコープに保存し，
			request.setAttribute("UserInfo", UserInfo);
			// 再ログインを促すJSPページにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp");
			dispatcher.forward(request, response);
		}else{
			// 認証成功のJSPページにフォワード
			request.setAttribute("UserInfo", UserInfo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/success.jsp");
			dispatcher.forward(request, response);
		}

	}
}
