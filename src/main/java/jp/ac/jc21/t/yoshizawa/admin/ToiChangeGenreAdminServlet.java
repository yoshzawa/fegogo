package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/toi/changeGenre" })
public class ToiChangeGenreAdminServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String genreId = request.getParameter("genreId");
		String toiId = request.getParameter("toiId");

		long tId = Long.parseLong(toiId);
		Toi t = Toi.getById(tId);
		
//		Genre oldGenre = t.getRefGenre().get();
//		oldGenre.removeToiRefList(tId);
//		oldGenre.save();

		Genre g = Genre.getById(Long.parseLong(genreId));

//		g.addToiRefList(t);
//		g.save();
		t.setGenre(g);
		t.save();

		response.sendRedirect("/admin/question/list?parentId=" + toiId);

	}
}