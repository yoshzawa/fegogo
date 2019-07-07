package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/question/update" })
public class QuestionUpdateAdminServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		long no = Long.parseLong(request.getParameter("No"));
		String Qname = request.getParameter("Qname");
		Long noOfOption = Long.parseLong(request.getParameter("noOfOption"));
		Long answer = Long.parseLong(request.getParameter("answer"));
		String parentId = request.getParameter("parentId");
		long pId = Long.parseLong(parentId);

		Question q = Question.getById(pId);
		q.setNo(no);
		q.setName(Qname);
		q.setNoOfOption(noOfOption);
		q.setAnswer(answer);
		q.setCreated(new Date());
		q = q.save();


		response.sendRedirect("/admin/question/list?parentId=" + q.getParent().getId());

	}

}