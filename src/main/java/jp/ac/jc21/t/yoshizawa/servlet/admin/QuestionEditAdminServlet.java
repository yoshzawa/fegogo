package jp.ac.jc21.t.yoshizawa.servlet.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/question/edit" })
public class QuestionEditAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		long id = Long.parseLong(request.getParameter("id"));

		Question q = Question.getById(id);
		request.setAttribute("q", q);
		
		Toi parent = q.getParent();
		Exam exam=parent.getExam();

		request.setAttribute("parent", parent);
		request.setAttribute("exam", exam);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/questionEditAdmin.jsp");
		rd.forward(request, response);
	}
}