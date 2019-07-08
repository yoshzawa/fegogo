package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
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

		Exam e = Exam.getById(pId);
		Toi t = Toi.createToi(e, no, toiName);
		t = t.save();
		e.addToiRefList(t);
		e.save();

//    RequestDispatcher rd = request.getRequestDispatcher("/jsp/examListAdmin.jsp");
//    rd.forward(request, response);
		response.sendRedirect("/admin/toi/list?parentId=" + parentId);

	}
}