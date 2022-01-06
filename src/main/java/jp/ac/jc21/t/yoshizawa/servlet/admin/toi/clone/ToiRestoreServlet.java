package jp.ac.jc21.t.yoshizawa.servlet.admin.toi.clone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.CloneQuestion;
import jp.ac.jc21.t.yoshizawa.objectify.CloneToi;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class ToiRestoreServlet
 */
@WebServlet("/admin/ToiRestoreServlet")
public class ToiRestoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromToiId = request.getParameter("fromToi");
		
		CloneToi cloneToi = CloneToi.getById(Long.parseLong(fromToiId));
		
		Toi copiedToi = Toi.getById(cloneToi.getToiId());
		Toi orgToi = Toi.getById(cloneToi.getOriginalToiId());
		
		List<CloneQuestion> cloneQuestions = CloneQuestion.getByToiId(copiedToi.getId());
		List<Question> copiedQuestion = new ArrayList<Question>();
		List<Question> orgQuestion = new ArrayList<Question>();
		for(CloneQuestion cq : cloneQuestions) {
			copiedQuestion.add(Question.getById(cq.getQuestionId()));
			orgQuestion.add(Question.getById(cq.getOriginalQuestionId()));
		}
		
		request.setAttribute("copiedToi", copiedToi);
		request.setAttribute("orgToi", orgToi);
		request.setAttribute("copiedQuestion", copiedQuestion);
		request.setAttribute("orgQuestion", orgQuestion);
		
		request.getRequestDispatcher("/WEB-INF/jsp/toiRestore.jsp").forward(request, response);
	}

}
