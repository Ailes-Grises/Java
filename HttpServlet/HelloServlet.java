// 1. 最低限以下のインポートは必要
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 2. HttpServlet クラスを継承する
public class HelloServlet extends HttpServlet{
	// 実行メソッドを記述する
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// Content-Type の記述
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("...");
		out.println("</html>");
	}
}
