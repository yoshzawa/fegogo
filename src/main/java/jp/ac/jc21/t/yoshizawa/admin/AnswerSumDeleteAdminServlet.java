package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.*;

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
		as.delete();
		
		
		
		
		
/*		
		List<AnswerSum> answerSumList = Member.get(memberId).getAnswerSumList();
		request.setAttribute("answerSumList", answerSumList);
		
		request.setAttribute("redirectTo", "/admin/answerSum/list?memberId="+ memberId );

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/answerSumListAllAdmin.jsp");
		rd.forward(request, response);
*/
	}
}