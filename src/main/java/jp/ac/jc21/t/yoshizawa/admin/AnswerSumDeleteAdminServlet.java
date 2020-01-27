package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/answerSum/delete" })
public class AnswerSumDeleteAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String memberId = request.getParameter("memberId");
		String AnswerSumId = request.getParameter("AnswerSumId");
		
		AnswerSum as = AnswerSum.getById(Long.parseLong(AnswerSumId));
		Map<Integer, Answer> mapanswer = as.getMapAnswer();
		
		response.getWriter().println("DELETE AnswerSumId:"+as.getId()+"<br />");
		
		for(Integer key : mapanswer.keySet()) {
			Answer answer = mapanswer.get(key);
			response.getWriter().println("DELETE AnswerId:"+answer.getId()+"<br />");
//			answer.delete();
		}
		as.deleteLink();
		
		
		
		
		
/*		
		List<AnswerSum> answerSumList = Member.get(memberId).getAnswerSumList();
		request.setAttribute("answerSumList", answerSumList);
		
		request.setAttribute("redirectTo", "/admin/answerSum/list?memberId="+ memberId );

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/answerSumListAllAdmin.jsp");
		rd.forward(request, response);
*/
	}
}