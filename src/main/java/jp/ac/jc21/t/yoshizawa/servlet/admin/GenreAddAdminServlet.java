package jp.ac.jc21.t.yoshizawa.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Genre;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/genre/add" })
public class GenreAddAdminServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String GenreName = request.getParameter("GenreName");

		Genre g = Genre.createGenre(GenreName);
		g.save();

		response.sendRedirect("/admin/genre/list");

	}
}