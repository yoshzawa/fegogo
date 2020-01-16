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
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/check/answerSum")
public class CheckAnswerSumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAnswerSumServlet() {
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
		
		String keyString = request.getParameter("answerSumId");
//		Exam e =Exam.getById(Long.parseLong(keyString));
		AnswerSum answerSum = AnswerSum.getById(Long.parseLong(keyString));
		
		answerSum.getRefMember();
		answerSum.getRefToi();
		
		Map<String, Ref<Answer>> answerMap = answerSum.getMapRefAnswer();
		
		List<String[]> list = new ArrayList<>();
		
		for(String key : answerMap.keySet()) {
			Ref<Answer> refAnswer = answerMap.get(key); 
			Answer answer = refAnswer.get();
			if(answer == null) {
				String[] s = new String[3];
				s[0]=null;
				s[1]=refAnswer.getKey().getId()+"";
				s[2]=key;
				list.add(s);
			}else {
				String[] s = new String[6];
				s[0]=answer.getId().toString();
				s[1]=answer.getDateString(answer.getAnswered()).toString();
				s[2]=answer.getNo().toString();
				s[3]=answer.getName();
				s[4]=answer.getAnswers();
				
				s[5]=null;
				Optional<Ref<AnswerSum>> refASum = Optional.ofNullable(answer.getRefAnswerSum());
				Optional<AnswerSum> aSum = Optional.ofNullable(refASum.get().get());
				if(aSum.isPresent()) {
					s[5]=aSum.get().getId().toString();
				}
				
				
				list.add(s);
				
			}
			
			
			
		}
		
		request.setAttribute("list", list);
		request.setAttribute("answerSum", answerSum);
		
		request.getRequestDispatcher("/jsp/checkAnswerSum.jsp").forward(request, response);
	}

}
