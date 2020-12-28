package jp.ac.jc21.t.yoshizawa.admin;

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

@WebServlet(urlPatterns = { "/admin/genre/detail" })
public class GenreDetailAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String genreId = request.getParameter("genreId");
		List<Genre> genreList = new ArrayList<>();
		genreList.add(Genre.getById(Long.parseLong(genreId)));
		request.setAttribute("genreList", genreList);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/genreDetailListAdmin.jsp");
		rd.forward(request, response);

	}


}