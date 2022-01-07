package jp.ac.jc21.t.yoshizawa.servlet.admin.question;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.CloneQuestion;

/**
 * Servlet implementation class QuestionCloneRemoveServlet
 */
@WebServlet("/admin/question/clone/remove")
public class QuestionCloneRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cloneQuestionId=request.getParameter("id");
		Optional<CloneQuestion> optCQ = Optional.ofNullable( CloneQuestion.getById(Long.parseLong(cloneQuestionId)));
		if(optCQ.isPresent()) {
			optCQ.get().delete();
		}
		
		response.sendRedirect("/admin/question/list3?parentId="+optCQ.get().getToiId());

	}

}
