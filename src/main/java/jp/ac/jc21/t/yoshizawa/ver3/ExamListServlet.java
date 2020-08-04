package jp.ac.jc21.t.yoshizawa.ver3;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

/**
 * Servlet implementation class IndexServlet
 */

@WebServlet(urlPatterns = { "/ver3/exam/list" })
public class ExamListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExamListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<Long, Exam> examMap = Exam.loadAll();
		request.setAttribute("examMap", examMap);
		
		Optional<String> optExamId = Optional.ofNullable(request.getParameter("examId"));
		
		Long examId = null;
		if(optExamId.isPresent()){
			examId=Long.parseLong(optExamId.get());
		}else{
			Long maxKey = Collections.max(examMap.keySet());
			examId=examMap.get(maxKey).getId();
		}
		request.setAttribute("examId", examId);
		
		

		
		
		
		
		
		RequestDispatcher rd;
			rd = request.getRequestDispatcher("/WEB-INF/jsp3/examList.jsp");

		rd.forward(request, response);

	}

}
