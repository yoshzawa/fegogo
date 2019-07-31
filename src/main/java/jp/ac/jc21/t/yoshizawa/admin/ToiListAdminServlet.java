package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/toi/list" })
public class ToiListAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String parentIdString = request.getParameter("parentId");
		long parentId = Long.parseLong(parentIdString);
		Exam e = Exam.getById(parentId);

		TreeMap<Long, Toi> toiMap = e.getToiMap();
		
		List<Genre> genreList = Genre.loadAll();

		request.setAttribute("parent", e);
		request.setAttribute("toiMap", toiMap);
		request.setAttribute("parentId", parentIdString);
		request.setAttribute("genreList", genreList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/toiListAdmin.jsp");
		rd.forward(request, response);

	}


}