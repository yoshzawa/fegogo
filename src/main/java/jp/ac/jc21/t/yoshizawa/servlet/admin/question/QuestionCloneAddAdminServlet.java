package jp.ac.jc21.t.yoshizawa.servlet.admin.question;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.CloneQuestion;
import jp.ac.jc21.t.yoshizawa.objectify.Question;

/**
 * Servlet implementation class QuestionCloneAddServlet
 */
@WebServlet("/admin/question/clone/add")
public class QuestionCloneAddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cloneQuestionId=request.getParameter("cloneQuestionId");
		String orgQuestionId=request.getParameter("orgQuestionId");
		
		Optional<Question> cloneQuestion = Optional.ofNullable(Question.getById(Long.parseLong(cloneQuestionId)));
		Optional<Question> orgQuestion = Optional.ofNullable(Question.getById(Long.parseLong(orgQuestionId)));
		
		if((cloneQuestion.isPresent())		&& (orgQuestion.isPresent() )) {
			Optional<CloneQuestion> oCQ = CloneQuestion.getByQuestionId(cloneQuestion.get().getId()).stream().findFirst();
			CloneQuestion cQ = oCQ.orElse(new CloneQuestion(cloneQuestion.get().getToiId(), 
					cloneQuestion.get().getId(), 
					orgQuestion.get().getId()));
			cQ.setOriginalQuestionId(orgQuestion.get().getId());
			cQ.save();
			
		}
		response.sendRedirect("/admin/question/list3?parentId="+cloneQuestion.get().getToiId());
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
