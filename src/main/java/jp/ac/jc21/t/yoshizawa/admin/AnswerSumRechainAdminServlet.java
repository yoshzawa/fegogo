package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import jp.ac.jc21.t.yoshizawa.objectify.*;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/answerSum/reChain" })
public class AnswerSumRechainAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String answerSumId =request.getParameter("answerSumId");
		String memberId =request.getParameter("memberId");
		
		AnswerSum as = AnswerSum.getById(Long.parseLong(answerSumId));
		Member m = Member.get(memberId);
		
		as.setRefMember(m);
		m.addRefAnswerSumList(as);
		
		as.save();
		m.save();
		
		response.sendRedirect("/admin/answerSum/listAll");
		
		

	}
}