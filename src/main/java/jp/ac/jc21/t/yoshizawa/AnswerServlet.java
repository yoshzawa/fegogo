package jp.ac.jc21.t.yoshizawa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.admin.QuestionListAdminServlet;
import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/answer" })
public class AnswerServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(AnswerServlet.class.getName());

		Map<String, String[]> paramMap = request.getParameterMap();
		
		// userIdが入っていない場合の処理
		if (paramMap.containsKey("userId") == false) {
			if (paramMap.containsKey("qId") == true) {
				log.info("no userId , one qId");
				RequestDispatcher rd = request
						.getRequestDispatcher("/question/list?parentId=" + paramMap.get("ParentId")[0]);
				rd.forward(request, response);
			} else {
				log.info("no userId , no qId");
				RequestDispatcher rd = request.getRequestDispatcher("/exam/list");
				rd.forward(request, response);
			}
		}

		// Formから送信されたデータごとにMapに入れる
		ArrayList<Answer> listAnswer = new ArrayList<Answer>();
		HashSet<Long> set = new HashSet<>();
		Set<String> keyset = paramMap.keySet();
		
		

		// 全ての問題のParentが同じか調べる
		String userId = request.getParameter("userId");
		
		for (String s : keyset) {
			if ((!s.equals("userId")) && (!s.equals("qId"))) {
				Long qKey = Long.parseLong(s);
				Question q = Question.getById(qKey);
				String[] answerArray = paramMap.get(s);
				Answer answer = Answer.createAnswer(userId, null, q, answerArray,q.getNo());
//				mapAnswer.put(q.getNo(), answer);
				listAnswer.add(answer);

				Long parentId = q.getParent().getId();
				set.add(parentId);
			}
		}
		if (set.size() != 1) {
			log.info("toi is not single");
			RequestDispatcher rd = request.getRequestDispatcher("/exam/list");
			rd.forward(request, response);
		}
		
		long key = (Long)set.toArray()[0];
		Toi t = Toi.getById(key);
		AnswerSum ansSummary = AnswerSum.createAnswerSum(userId, t, -1, null);
		ansSummary.save();
		
		int correct=0;
		Map<String,Ref<Answer>> mapAnswer  = new HashMap<>();
		for(Answer a:listAnswer) {
			a.setAnswerSum(ansSummary);
			a = 			a.save();
			mapAnswer.put(a.getNo(), Ref.create(a));
			if(a.isCorrect() == true) {
				correct++;
			}
		}
		ansSummary.setNoOfSeikai(correct);
		ansSummary.setMapRefAnswer(mapAnswer);
		
		

		
		
//		request.setAttribute("examList", examList);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/examList.jsp");
		rd.forward(request, response);

	}
}