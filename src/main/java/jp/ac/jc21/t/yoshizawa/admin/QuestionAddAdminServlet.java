package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.util.logging.Logger;

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

@WebServlet(urlPatterns = { "/admin/question/add" })
public class QuestionAddAdminServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(QuestionListAdminServlet.class.getName());

		long no = Long.parseLong(request.getParameter("No"));
		String Qname = request.getParameter("Qname");
		String noOfOption = request.getParameter("noOfOption");
		String answer = request.getParameter("answer");
		String parentId = request.getParameter("parentId");
		long pId = Long.parseLong(parentId);

		Toi t = Toi.getById(pId);
		Question q = Question.createQuestion(t, no, Qname, Long.parseLong(noOfOption), Long.parseLong(answer));
		q = q.save();
		t.addQuestion(q);
		log.info("["+request.getServletPath() + "] t.getQuestionListSize() = " + t.getQuestionListSize());
		t.save();


		response.sendRedirect("/admin/question/list?parentId=" + parentId);

	}

}