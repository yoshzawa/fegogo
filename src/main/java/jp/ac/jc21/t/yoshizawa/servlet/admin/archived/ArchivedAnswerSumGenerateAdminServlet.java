package jp.ac.jc21.t.yoshizawa.servlet.admin.archived;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.archived.ArchivedAnswer;
import jp.ac.jc21.t.yoshizawa.objectify.archived.ArchivedAnswerSum;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/archivedAnswerSum/generate" })
public class ArchivedAnswerSumGenerateAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Optional<Member> optMember = Optional.ofNullable(Member.getByeMail(request.getParameter("email")));

		if (optMember.isPresent()) {
			Member member = optMember.get();
			// USERごとのAnswerSum取得、toiIdごとのMAPを作成
			List<AnswerSum> answerSumList = member.getAnswerSumList();
			Map<Long, List<AnswerSum>> map = AnswerSum.makeMapByToiId(answerSumList);

			List<Answer> answerList = member.getAnswerList();
			Map<Long, List<Answer>> map2 = Answer.makeMapByQuestionId(answerList);

			for (Long toiId : map.keySet()) {

				// toiごとに処理する
				response.getWriter().println("ToiId ->" + toiId);
				System.err.println("ToiId ->" + toiId);
				List<AnswerSum> list = map.get(toiId);
				ArchivedAnswerSum arcAnsSum = ArchivedAnswerSum.generate(list);
				arcAnsSum.save();

				// questionごとの処理

				for (Question q : Question.getListByToiId(toiId)) {
					response.getWriter().println("QuestionId ->" + q.getId());
					System.err.println("QuestionId ->" + q.getId());
					List<Answer> list2 = map2.get(q.getId());
					List<ArchivedAnswer> listArcAnswer = ArchivedAnswer.generate(list2, toiId, arcAnsSum);
					ArchivedAnswer.save(listArcAnswer);
				}
			}

		}
	}
}