package jp.ac.jc21.t.yoshizawa;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.admin.QuestionListAdminServlet;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/question/list" })
public class QuestionListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(QuestionListAdminServlet.class.getName());

		String parentIdString = request.getParameter("parentId");

		long parentId = Long.parseLong(parentIdString);
		log.info("["+request.getServletPath() + "]parentId:" + parentId);

		Toi parent = Toi.getById(parentId);

		Exam exam = parent.getParent();
		
		TreeMap<Long, Question> qMap = Toi.getQuestionMap(parent);
		
		request.setAttribute("parent", parent);
		request.setAttribute("parentId", parentIdString);
		request.setAttribute("questionMap", qMap);
		request.setAttribute("exam", exam);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/questionList.jsp");
		rd.forward(request, response);

	}
}