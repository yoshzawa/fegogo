package jp.ac.jc21.t.yoshizawa.ver2;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/question/list","/question2/list" })
public class Question2ListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(Question2ListServlet.class.getName());

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		if (email == null) {

			RequestDispatcher rd = request.getRequestDispatcher("/question2/NoLog/list");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/question2/Login/list");
			rd.forward(request, response);
		}

	}
}