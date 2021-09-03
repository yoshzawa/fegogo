package jp.ac.jc21.t.yoshizawa.servlet.admin.answerSum;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.*;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/answerSum/reChain" })
public class AnswerSumRechainAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String answerSumId =request.getParameter("answerSumId");
		String memberId =request.getParameter("memberId");
		String redirectTo =request.getParameter("redirectTo");
		
		AnswerSum as = AnswerSum.getById(Long.parseLong(answerSumId));
		Member m = Member.get(memberId);
		
//		as.setMember(m);
		as.setName(memberId);
//		m.addRefAnswerSumList(as);
		
		as.save();
//		m.save();
		
		response.sendRedirect(redirectTo);
		
		

	}
}