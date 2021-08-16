package jp.ac.jc21.t.yoshizawa.servlet.admin;

import java.io.IOException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Genre;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/genre/list" })
public class GenreListAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Genre> genreList = Genre.loadAll();
		request.setAttribute("genreList", genreList);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/genreListAdmin.jsp");
		rd.forward(request, response);

	}


}