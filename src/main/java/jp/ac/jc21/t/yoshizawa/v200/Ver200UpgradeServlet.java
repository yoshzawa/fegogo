package jp.ac.jc21.t.yoshizawa.v200;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class Ver200UpgradeServlet
 */
@WebServlet("/upgrade")
public class Ver200UpgradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		Long examId = Long.parseLong("5751404605997056");
		Exam e = Exam.getById(examId);
		
		List<Toi> tois = Toi.getToiListByExamId(examId);
		for(Toi t : tois) {
			response.getWriter().println(t.getName());
		}
	}

}
