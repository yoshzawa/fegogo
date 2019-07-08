package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/question/addMulti" })
public class QuestionAddMultiAdminServlet extends HttpServlet {


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		long no = Long.parseLong(request.getParameter("No"));
		String Qname = request.getParameter("Qname");
		String noOfOption = request.getParameter("noOfOption");
//		String answer = request.getParameter("answer");
		String[] correctList = request.getParameterValues("correct");
		int correctLength = correctList.length;
		int[] correct = new int[correctLength];
		for(int i=0 ; i<correctLength;i++) {
			correct[i] = Integer.parseInt(correctList[i]);
		}
		
		
		
		String parentId = request.getParameter("parentId");
		long pId = Long.parseLong(parentId);

		Toi t = Toi.getById(pId);
		Question q = Question.createMultiQuestion(t, no, Qname, Long.parseLong(noOfOption), correct);
		//createQuestion(t, no, Qname, Long.parseLong(noOfOption), Long.parseLong(answer));
		q = q.save();
		t.addQuestionRefList(q);
		t.save();


		response.sendRedirect("/admin/question/list?parentId=" + parentId);

	}
}