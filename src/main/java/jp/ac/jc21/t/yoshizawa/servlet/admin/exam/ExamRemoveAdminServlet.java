package jp.ac.jc21.t.yoshizawa.servlet.admin.exam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

/**
 * Servlet implementation class ExamRemoveAdminServlet
 */
@WebServlet("/admin/exam/remove")
public class ExamRemoveAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExamRemoveAdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String examId = request.getParameter("examId");
		Exam e = Exam.getById(examId);
		e.delete();
		
		response.sendRedirect("/admin/");


	}

}
