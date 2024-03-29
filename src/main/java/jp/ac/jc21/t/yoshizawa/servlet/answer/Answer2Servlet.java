package jp.ac.jc21.t.yoshizawa.servlet.answer;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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
@WebServlet(urlPatterns = { "/answer", "/answer2" })
public final class Answer2Servlet extends HttpServlet {

	@Override
	public final void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
		
		Map<String, String[]> paramMap = request.getParameterMap();
		
		String paramUserId=paramMap.get("userId")[0];
		String paramToiId=paramMap.get("toiId")[0];
		
		// AnswerSumを作成する
		AnswerSum aSum = new AnswerSum();
		aSum.setName(paramUserId);
		aSum.setAnswered(new Date());
		aSum.setNoOfSeikai(-1);
		aSum.setName(paramUserId);
		aSum.setToiId(Long.parseLong(paramToiId));

		// AnswerSumを保存する
		aSum.save();
		
		// Answerを作成する
		Map<String, Ref<Answer>> mapRefAnswer = new TreeMap<String, Ref<Answer>>();
		int correct = 0;

		Ref<AnswerSum> refAsum = Ref.create(aSum);
		Set<String> paramMapKey = paramMap.keySet();
				for(String name : paramMapKey) {
			switch (name) {
			case "userId":
			case "toiId":
				break;
			default:
				// AnswerにQuestionを登録
				Question question = Question.getById(Long.parseLong(name));
				Answer answer = Answer.createAnswer(paramUserId, refAsum, question, paramMap.get(name));
				// Answerを保存する
				answer.save();
				mapRefAnswer.put(answer.getNo(), Ref.create(answer));
				if (answer.isCorrect() == true) {
					correct++;
				}
			}
		}
		// AnswerSumを再度保存する
		aSum.setNoOfSeikai(correct);
//		aSum.setMapRefAnswer(mapRefAnswer);
		aSum.save();

		// idを確認する
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		request.setAttribute("email", email);


		boolean error=false;

		error = check1(request, response, paramMap, paramUserId, paramToiId, aSum);
		// userIdが違う場合の処理
		error = error || check2(request, response, paramUserId, paramToiId, aSum, email);
		
		// 過去のAnswerSumを確認する
		error = error ||check3(request, response, paramUserId, paramToiId, aSum, email);
		
		if(error == false) {
			// AnswerSumにToiを登録
			Toi toi = Toi.getById(Long.parseLong(paramToiId));
			aSum.setToiId(toi.getId());
			// AnswerSumにMemberを登録
				Member member = Member.get(paramUserId);
				aSum.setName(paramUserId);
			// AnswerSumを保存する
				toi.save();
				aSum.save();
//				RequestDispatcher rd = request.getRequestDispatcher("/answer2/show?answerSumId="+aSum.getId());
//				rd.forward(request, response);
				response.sendRedirect("/answer2/show?answerSumId="+aSum.getId());

		}
	}

	private boolean  check3(HttpServletRequest request, HttpServletResponse response, String paramUserId, String paramToiId,
			AnswerSum aSum, String email) throws ServletException, IOException {
		final Logger log = Logger.getLogger(Answer2Servlet.class.getName());

		boolean error = false;

		List<AnswerSum> list = AnswerSum.getListByEMail(email);
		for(AnswerSum ansSum:list) {
			if(ansSum.getToiId().equals( Long.parseLong(paramToiId))) {
				long diff = aSum.getAnswered().getTime() - ansSum.getAnswered().getTime();
				diff /= 1000;
				System.out.println(aSum.getId() +"("+aSum.getAnswered()+"):" +
						ansSum.getId() + "(" + ansSum.getAnswered()+")["+diff+"]");
				if((diff != 0)&&(diff<240*60*60)) {
					log.warning("interval too short");

					RequestDispatcher rd = request
							.getRequestDispatcher("/answer2/drop?status=4&answerSumId=" + aSum.getId()+"&masterAnswerSumId="+ansSum.getId()
							+"&userId="+paramUserId+"&toiId="+paramToiId);
					rd.forward(request, response);
					error = true;

				}
			}
		}
		return error;

	}

	private boolean check2(HttpServletRequest request, HttpServletResponse response, String paramUserId,
			String paramToiId, AnswerSum aSum, String email) throws ServletException, IOException {
		final Logger log = Logger.getLogger(Answer2Servlet.class.getName());

		boolean error = false;

		if ((email == null) || (!email.equals(request.getParameter("userId")))) {
			log.warning("email error");
			RequestDispatcher rd = request.getRequestDispatcher("/answer2/drop?status=3&answerSumId=" + aSum.getId()
					+ "&userId=" + paramUserId + "&toiId=" + paramToiId);
			rd.forward(request, response);
			error = true;

		}
		return error;
	}

	private boolean check1(HttpServletRequest request, HttpServletResponse response, 
			Map<String, String[]> paramMap, String paramUserId, String paramToiId, AnswerSum aSum)
			throws ServletException, IOException {
		final Logger log = Logger.getLogger(Answer2Servlet.class.getName());

		boolean error = false;
		// userIdが入っていない場合の処理
		if (paramMap.containsKey("userId") == false) {
			if (paramMap.containsKey("toiId") == true) {
				log.warning("no userId , one toiId");
				RequestDispatcher rd = request.getRequestDispatcher("/answer2/drop?status=1&answerSumId=" + aSum.getId()
						+ "&userId=" + paramUserId + "&toiId=" + paramToiId);
				rd.forward(request, response);
				error = true;
			} else {
				log.warning("no userId , no toiId");
				RequestDispatcher rd = request.getRequestDispatcher("/answer2/drop?status=2&answerSumId=" + aSum.getId()
						+ "&userId=" + paramUserId + "&toiId=" + paramToiId);
				rd.forward(request, response);
				error = true;
			}
		}
		return error;
	}

	public final void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		final Logger log = Logger.getLogger(Answer2Servlet.class.getName());
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		log.warning("/answer:doGet(405) user=" + email);
		response.sendRedirect("/");
	}

}