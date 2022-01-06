package jp.ac.jc21.t.yoshizawa.servlet.admin.toi.clone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.CloneQuestion;
import jp.ac.jc21.t.yoshizawa.objectify.CloneToi;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class ToiRestoreServlet
 */
@WebServlet("/admin/answerSumRestoreCheck")
public class AnswerSumRestoreCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String answerSumId = request.getParameter("answerSumId");
		
		AnswerSum aSum = AnswerSum.getById(Long.parseLong(answerSumId));
		
		
		
		
		CloneToi cloneToi = CloneToi.getByToiId(aSum.getToiId()).get(0);
		
		Long copiedToiId = cloneToi.getToiId();
		Long orgToiId = cloneToi.getOriginalToiId();

		Toi copiedToi = Toi.getById(copiedToiId);
		Toi orgToi = Toi.getById(orgToiId);
		
		List<CloneQuestion> cloneQuestions = CloneQuestion.getByToiId(copiedToi.getId());
		List<Question> copiedQuestion = new ArrayList<Question>();
		List<Question> orgQuestion = new ArrayList<Question>();
		for(CloneQuestion cq : cloneQuestions) {
			copiedQuestion.add(Question.getById(cq.getQuestionId()));
			orgQuestion.add(Question.getById(cq.getOriginalQuestionId()));
		}
		
		request.setAttribute("aSum", aSum);
		request.setAttribute("copiedToi", copiedToi);
		request.setAttribute("orgToi", orgToi);
		request.setAttribute("copiedQuestion", copiedQuestion);
		request.setAttribute("orgQuestion", orgQuestion);
		
		request.getRequestDispatcher("/jsp/answerSumRestoreCheck.jsp").forward(request, response);
	}

}
