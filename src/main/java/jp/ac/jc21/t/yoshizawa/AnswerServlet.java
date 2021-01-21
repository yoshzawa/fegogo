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
import javax.servlet.http.HttpSession;

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/answer1" })
public final class AnswerServlet extends HttpServlet {

	@Override
	public final void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		final Logger log = Logger.getLogger(AnswerServlet.class.getName());

		Map<String, String[]> paramMap = request.getParameterMap();

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		// userIdが入っていない場合の処理
		if (paramMap.containsKey("userId") == false) {
			if (paramMap.containsKey("toiId") == true) {
				log.info("no userId , one toiId");
				RequestDispatcher rd = request
						.getRequestDispatcher("/question/list?parentId=" + paramMap.get("ParentId")[0]);
				rd.forward(request, response);
			} else {
				log.info("no userId , no toiId");
				RequestDispatcher rd = request.getRequestDispatcher("/");
				rd.forward(request, response);
			}
		}
		if ((email == null) || (!email.equals(request.getParameter("userId")))) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/question/list?parentId=" + paramMap.get("ParentId")[0]);
			rd.forward(request, response);
		}

		// Formから送信されたデータごとにMapに入れる
		HashMap<Long, Answer> answerMap = new HashMap<>();
		HashSet<Long> parentSet = new HashSet<>();
		Set<String> paramMapKeyset = paramMap.keySet();

		String userId = request.getParameter("userId");

		// 全ての問題のParentが同じか調べる
		Member member = Member.get(userId);

		for (String s : paramMapKeyset) {
			if ((!s.equals("userId")) && (!s.equals("toiId"))) {
				Long qKey = Long.parseLong(s);
				Question q = Question.getById(qKey);
				String[] answerArray = paramMap.get(s);
				Answer answer = Answer.createAnswer(userId, null, q, answerArray, q.getNo());
				answerMap.put(qKey, answer);

				Long parentId = q.getParent().getId();
				parentSet.add(parentId);
			}
		}
		if (parentSet.size() != 1) {
			log.info("toi is not single");
			RequestDispatcher rd = request.getRequestDispatcher("/exam/list");
			rd.forward(request, response);
		}

		long toiKey = (Long) parentSet.toArray()[0];
		Toi toi = Toi.getById(toiKey);
		AnswerSum ansSummary = AnswerSum.createAnswerSum(userId, toi, -1, null);
		ansSummary.save();
		toi.addAnswerSumRefList(ansSummary);
		toi.save();

		int correct = 0;
		Map<String, Ref<Answer>> mapAnswer = new HashMap<>();

		List<Ref<Question>> qList = toi.getQuestionRefList();
		for (Ref<Question> q : qList) {
			Question question = q.get();
			Answer a = answerMap.get(question.getId());
			if (a == null) {
				a = Answer.createAnswer(userId, null, question, new String[0], question.getNo());
			}
			a.setAnswerSum(ansSummary);
			a = a.save();
			mapAnswer.put(a.getNo(), Ref.create(a));

			if (a.isCorrect() == true) {
				correct++;
			}
		}

		ansSummary.setNoOfSeikai(correct);
		ansSummary.setMapRefAnswer(mapAnswer);
		ansSummary.setMember(member);
		ansSummary.save();

		member.addRefAnswerSumList(ansSummary);
		member.save();

		request.setAttribute("ansSummary", ansSummary);

		request.setAttribute("email", email);

		List<String[]> datas = new ArrayList<String[]>();
		Map<Integer, Answer> answer = ansSummary.getMapAnswer();
		Set<Integer> keyset = answer.keySet();

		for (Integer i : keyset) {
			Answer a = answer.get(i);
			String[] s = new String[3];
			Question question = a.getQuestion();

			s[0] = question.getName();
			s[1] = question.getAnswers();
			s[2] = a.getAnswers();
			datas.add(s);

		}
		request.setAttribute("datas", datas);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/login/answer.jsp");
		rd.forward(request, response);

	}
	public final void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		final Logger log = Logger.getLogger(AnswerServlet.class.getName());
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		log.warning("/answer:doGet(405) user=" + email);
		response.sendRedirect("/");
	}

}