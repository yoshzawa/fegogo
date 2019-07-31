package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.util.logging.Logger;

import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/question/list" })
public class QuestionListAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(QuestionListAdminServlet.class.getName());

		// ���ID�����o��
		String parentIdString = request.getParameter("parentId");
		request.setAttribute("parentId", parentIdString);


		// ������o��
		long parentId = Long.parseLong(parentIdString);
		Toi parent = Toi.getById(parentId);
		request.setAttribute("parent", parent);

		// ���������o��
		Exam exam = parent.getParent();
		request.setAttribute("exam", exam);

		// �ݖ�����o��
		TreeMap<Long, Question> qMap = Toi.getQuestionMap(parent);
		request.setAttribute("questionMap", qMap);
		
		List<Genre> genreList = Genre.loadAll();
		request.setAttribute("genreList", genreList);

		
		

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/questionListAdmin.jsp");
		rd.forward(request, response);

	}

}