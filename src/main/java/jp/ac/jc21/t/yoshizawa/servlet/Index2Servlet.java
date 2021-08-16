package jp.ac.jc21.t.yoshizawa.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class IndexServlet
 */

@WebServlet(urlPatterns = { "/index","/index2" })
public class Index2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Index2Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		RequestDispatcher rd;
		if (email == null) {
			rd = request.getRequestDispatcher("/WEB-INF/jsp2/nolog/index.jsp");
		} else {
			request.setAttribute("email", email);

			rd = request.getRequestDispatcher("/WEB-INF/jsp2/login/indexLogin.jsp");
		}
		rd.forward(request, response);

	}

}
