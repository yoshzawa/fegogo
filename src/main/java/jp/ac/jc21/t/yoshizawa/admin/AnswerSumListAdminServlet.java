package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.*;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/answerSum/list" })
public class AnswerSumListAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String memberId = request.getParameter("memberId");
		
		List<AnswerSum> answerSumList = Member.get(memberId).getAnswerSumList();
		request.setAttribute("answerSumList", answerSumList);
		
		request.setAttribute("redirectTo", "/admin/answerSum/list?memberId="+ memberId );

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/answerSumListAllAdmin.jsp");
		rd.forward(request, response);

	}
}