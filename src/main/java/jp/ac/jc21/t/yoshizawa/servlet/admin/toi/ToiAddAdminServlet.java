package jp.ac.jc21.t.yoshizawa.servlet.admin.toi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/toi/add" })
public class ToiAddAdminServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		long no = Long.parseLong(request.getParameter("No"));
		String toiName = request.getParameter("toiName");
		String parentId = request.getParameter("parentId");
		long pId = Long.parseLong(parentId);
		String genreId = request.getParameter("genreId");
		Genre g = Genre.getById(Long.parseLong(genreId));

		Exam e = Exam.getById(pId);
		Toi t = Toi.createToi(e, no, toiName,g);
		t = t.save();
//		e.addToiRefList(t);
		e.save();
		
//		g.addToiRefList(t);
//		g.save();

		response.sendRedirect("/admin/toi/list?parentId=" + parentId);

	}
}