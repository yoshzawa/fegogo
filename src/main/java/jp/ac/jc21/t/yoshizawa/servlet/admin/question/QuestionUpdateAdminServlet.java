package jp.ac.jc21.t.yoshizawa.servlet.admin.question;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Question;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/question/update" })
public class QuestionUpdateAdminServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		long no = Long.parseLong(request.getParameter("No"));
		String Qname = request.getParameter("Qname");
		Long noOfOption = Long.parseLong(request.getParameter("noOfOption"));
		String parentId = request.getParameter("parentId");
		long pId = Long.parseLong(parentId);
		String[] correct = request.getParameterValues("correct");

		Question q = Question.getById(pId);
		q.setNo(no);
		q.setName(Qname);
		q.setNoOfOption(noOfOption);
		q.newAnswerSet();
		q.setAnswerSet(correct);
		q.setCreated(new Date());
		q = q.save();


		response.sendRedirect("/admin/question/list?parentId=" + q.getParent().getId());

	}

}