package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Question;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/question/add" })
public class QuestionEditAdminServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("id"));

		Question q = Question.getById(id);
		request.setAttribute("q", q);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/questionEditAdmin.jsp");
		rd.forward(request, response);
	}
}