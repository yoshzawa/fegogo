package jp.ac.jc21.t.yoshizawa.servlet.admin.question;

import java.io.IOException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.impl.orbutil.CacheTable;

import jp.ac.jc21.t.yoshizawa.objectify.CloneToi;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/question/list", "/admin/question/list1" })
public class QuestionList1AdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// 問のIDを取り出す
		String parentIdString = request.getParameter("parentId");
		request.setAttribute("parentId", parentIdString);

		// 問を取り出す
		long parentId = Long.parseLong(parentIdString);
		Toi parent = Toi.getById(parentId);
		request.setAttribute("parent", parent);

		// 試験を取り出す
		Exam exam = parent.getExam();
		request.setAttribute("exam", exam);

		// 設問を取り出す
		TreeMap<Long, Question> qMap = Toi.getQuestionMap(parent);
		request.setAttribute("questionMap", qMap);

		List<Genre> genreList = Genre.loadAll();
		request.setAttribute("genreList", genreList);

		if(CloneToi.getByToiId(parentId).size()>0) {
			//cloneされた
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/questionListAdmin1cloned.jsp");
			rd.forward(request, response);
		}else {
			//cloneされたものではない
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/questionListAdmin1.jsp");
			rd.forward(request, response);
		}
	}

}