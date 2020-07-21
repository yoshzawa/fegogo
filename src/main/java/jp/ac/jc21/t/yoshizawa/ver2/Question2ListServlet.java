package jp.ac.jc21.t.yoshizawa.ver2;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ac.jc21.t.yoshizawa.admin.QuestionListAdminServlet;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/question2/list" })
public class Question2ListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(QuestionListAdminServlet.class.getName());

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