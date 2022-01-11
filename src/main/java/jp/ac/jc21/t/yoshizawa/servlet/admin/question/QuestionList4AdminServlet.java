package jp.ac.jc21.t.yoshizawa.servlet.admin.question;

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

@WebServlet(urlPatterns = { "/admin/question/list4" })
public class QuestionList4AdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// ���ID�����o��
		String toiIdString = request.getParameter("parentId");
		Long toiId = Long.parseLong(toiIdString);
		request.setAttribute("toiId", toiId);
		Toi parent = Toi.getById(toiId);
				request.setAttribute("parent",parent);

		
		List<AnswerSum> answerSumList = AnswerSum.getListByToiId(toiId);
		request.setAttribute("answerSumList", answerSumList);
		
		request.setAttribute("redirectTo", "/admin/toi/list?parentId="+ toiIdString );

		if(CloneToi.getByToiId(toiId).size()>0) {
			//clone���ꂽ
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/questionListAdmin4cloned.jsp");
			rd.forward(request, response);
		}else {
			//clone���ꂽ���̂ł͂Ȃ�
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/questionListAdmin4.jsp");
			rd.forward(request, response);
		}

	}
}