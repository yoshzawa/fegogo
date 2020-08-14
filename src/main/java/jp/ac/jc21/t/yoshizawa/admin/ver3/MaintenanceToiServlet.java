package jp.ac.jc21.t.yoshizawa.admin.ver3;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(urlPatterns = { "/admin/ver3/maintenance/toi" })
public class MaintenanceToiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaintenanceToiServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<Long, Exam> examMap = Exam.loadAll();
		for (Long key : examMap.keySet()) {
			Exam e = examMap.get(key);
			System.out.println("Exam :" + e.getName());
			e.resetToiRefList();
			e.save();
		}

//		RequestDispatcher rd = request.getRequestDispatcher("/jsp/questionEditAdmin.jsp");
//		rd.forward(request, response);
	}

}
