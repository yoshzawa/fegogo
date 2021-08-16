package jp.ac.jc21.t.yoshizawa.servlet.admin.answerSum;

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

		String AnswerSumId = request.getParameter("AnswerSumId");
		
		AnswerSum as = AnswerSum.getById(Long.parseLong(AnswerSumId));
		Map<Integer, Answer> mapanswer = as.getMapAnswer();
		
		response.getWriter().println("DELETE AnswerSumId:"+as.getId()+"<br />");
		
		for(Integer key : mapanswer.keySet()) {
			Answer answer = mapanswer.get(key);
			response.getWriter().println("DELETE AnswerId:"+answer.getId()+"<br />");
		}
		as.deleteLink();
	}
}