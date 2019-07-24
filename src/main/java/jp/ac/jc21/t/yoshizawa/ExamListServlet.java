package jp.ac.jc21.t.yoshizawa;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Member;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/exam/list" , "/index" })
public class ExamListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Exam> examList = Exam.loadAll();
		request.setAttribute("examList", examList);
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		
		if(email == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/examList.jsp");
			rd.forward(request, response);			
		} else {
			request.setAttribute("email", email);
			
			Member member=Member.get(email);
			List<AnswerSum> asl = member.getAnswerSumList();
			request.setAttribute("answerSumList", asl);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/examListLogin.jsp");
			rd.forward(request, response);
		}



	}
}