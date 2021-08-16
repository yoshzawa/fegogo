package jp.ac.jc21.t.yoshizawa.admin.ver5;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.datastore.Exam;

/**
 * Servlet implementation class Exam2Servlet
 * https://googleapis.dev/java/google-cloud-datastore/latest/index.html
 */
@WebServlet("/admin/exam2")
public class Exam2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");

		Exam e = Exam.createExam((long) 202104, "óﬂòa03îNìxètä˙");
		e.save();
		e.setName("óﬂòa03îNìxè„ä˙");
		e.save();
		
		
		List<Exam> exams = Exam.loadAllList();
	
		request.setAttribute("exams", exams);
		
		request.getRequestDispatcher("/WEB-INF/jsp5/exam2.jsp").forward(request, response);


	}

}
