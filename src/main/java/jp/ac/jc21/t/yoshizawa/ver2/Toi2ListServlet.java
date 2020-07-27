package jp.ac.jc21.t.yoshizawa.ver2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/toi2/list" })
public class Toi2ListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// ÉÜÅ[ÉUÅ[èÓïÒéÊìæ
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");


		if (email == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/toi2/NoLog/list");
			rd.forward(request, response);

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/toi2/login/list");
			rd.forward(request, response);

		}
	}
}