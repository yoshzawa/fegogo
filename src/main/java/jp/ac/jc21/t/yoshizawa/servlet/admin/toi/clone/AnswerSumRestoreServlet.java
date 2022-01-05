package jp.ac.jc21.t.yoshizawa.servlet.admin.toi.clone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.CloneQuestion;
import jp.ac.jc21.t.yoshizawa.objectify.CloneToi;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class ToiRestoreServlet
 */
@WebServlet("/admin/answerSumRestore")
public class AnswerSumRestoreServlet extends HttpServlet {
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
		
		System.out.println("AnswerSum.toiId:" + aSum.getToiId() + "->" + orgToi.getId());
		
		aSum.setToiId(orgToi.getId());
		aSum.save();
		
		List<Answer> ListAns = aSum.getAnswerList();
		for(Answer ans : ListAns) {
			Long qId = ans.getQuestionId();
			List<CloneQuestion> listCQ = CloneQuestion.getByQuestionId(qId);
			System.out.println("Answer.QuestionId:" + ans.getQuestionId() + "->" + listCQ.get(0).getOriginalQuestionId());
			ans.setQuestionId(listCQ.get(0).getOriginalQuestionId());
			ans.save();
		}
		
		
		
		
	}

}
