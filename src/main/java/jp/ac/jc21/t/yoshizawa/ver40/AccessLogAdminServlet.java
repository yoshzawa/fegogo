package jp.ac.jc21.t.yoshizawa.ver40;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.AccessLog;

/**
 * Servlet implementation class AccessLogAdminServlet
 */
@WebServlet("/admin/access.log")
public class AccessLogAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccessLogAdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<AccessLog> aLog = AccessLog.loadAll();
		for (AccessLog a : aLog) {
			out.print(a.getName());
			out.print(",");
			out.print(a.getAccessed());
			out.print(",");
			out.print(a.getOperation());
			out.println();
		}
	}

}
