package jp.ac.jc21.t.yoshizawa.admin.check;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/delete/answerSum")
public class DeleteAnswerSumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAnswerSumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setContentType("text/csv; charset=Windows-31J");
//		PrintWriter out = response.getWriter();
		
		String answerSumKeyString = request.getParameter("answerSumId");
		AnswerSum answerSum =AnswerSum.getById(Long.parseLong(answerSumKeyString));

		// answerÇçÌèú
		
		Map<String, Ref<Answer>> answerMap = answerSum.getMapRefAnswer();
		for(String key : answerMap.keySet()) {
			Ref<Answer> refAnswer = answerMap.get(key); 
			Optional<Answer> answer = Optional.ofNullable(refAnswer.get());
			if(answer.isPresent()) {
				System.out.println("answer:"+answer.get().getId());
			}
		}
		
		// memberÇ©ÇÁçÌèú
		
		Optional<Member> member = answerSum.getMember();
		if(member.isPresent()) {
			System.out.println("member:"+member.get().geteMail());
			List<Ref<AnswerSum>> refAnswerSumList = member.get().getRefAnswerSumList();
			System.out.println("member:"+refAnswerSumList.size());
			refAnswerSumList.remove(Ref.create(answerSum));
			System.out.println("member:"+refAnswerSumList.size());
		}
		
		Optional<Toi> toi = answerSum.getToi();
		if(toi.isPresent()) {
			System.out.println("toi:"+toi.get().getId());
			List<Ref<AnswerSum>> answerSumRefList = toi.get().getAnswerSumRefList();
			System.out.println("toi:"+answerSumRefList.size());
			answerSumRefList.remove(Ref.create(answerSum));
			System.out.println("toi:"+answerSumRefList.size());
		}

			
			

		
		
		response.sendRedirect("/admin/check/answerSum?answerSumId="+answerSumKeyString);

		


		
		
	}

}
