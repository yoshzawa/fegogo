package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/toi/addAnswerSum" })
public class ToiChangeGenreAdminServlet2 extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//http://localhost:8080/admin/toi/addAnswerSum?toiId=5769501089988608&answerSumId=5137941315715072
			
		
		String answerSumId = request.getParameter("answerSumId");
		String toiId = request.getParameter("toiId");

		AnswerSum a = AnswerSum.getById(Long.parseLong(answerSumId));
		Toi t = Toi.getById(Long.parseLong(toiId));

		a.setRefToi(Ref.create(t));
		a.save();
		t.addAnswerSumRefList(a);
		t.save();
		
		

		response.sendRedirect("/admin/answerSum");

	}
}