package jp.ac.jc21.t.yoshizawa.servlet.admin.toi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/toi/addAnswerSum" })
public class ToiChangeGenreAdminServlet2 extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String answerSumId = request.getParameter("answerSumId");
		String toiId = request.getParameter("toiId");

		AnswerSum a = AnswerSum.getById(Long.parseLong(answerSumId));
		Toi t = Toi.getById(Long.parseLong(toiId));

		a.setToiId(t.getId());
		a.save();
		t.save();

		response.sendRedirect("/admin/answerSum");

	}
}