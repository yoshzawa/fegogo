package jp.ac.jc21.t.yoshizawa.ver3;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class IndexServlet
 */

@WebServlet(urlPatterns = { "/ver3/genre/list" })
public class GenreListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenreListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Genre> genreList = Genre.loadAll();
		request.setAttribute("genreList", genreList);
		Genre g = genreList.get(0);
		Long gId = g.getId();
		request.setAttribute("gId", gId);

		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/WEB-INF/jsp3/genreList.jsp");

		rd.forward(request, response);

	}

}
