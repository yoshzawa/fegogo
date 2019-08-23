package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import jp.ac.jc21.t.yoshizawa.AnswerServlet;
import jp.ac.jc21.t.yoshizawa.objectify.*;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/answer/dumpAnswer.csv" })
public class AnswerDumpAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(AnswerDumpAdminServlet.class.getName());

		response.setContentType("text/csv; charset=Windows-31J");
		PrintWriter out = response.getWriter();

		List<AnswerSum> answerSumList = AnswerSum.loadAll();

		UserService userService = UserServiceFactory.getUserService();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		out.println("–âid,‰ğ“šÒ,‰ğ“š“ú,Œ±,–â,•ª–ì‡,•ª–ì,–âÚ×,İ–âid,o‘è‡,İ–â,³‰ğ,‰ğ“š,³Œë");

		for (AnswerSum as : answerSumList) {
			Toi toi = as.getRefToi().get();
			Exam exam = toi.getExam();
			Map<Integer, Answer> answerMap = as.getMapAnswer();
			for (Integer key : answerMap.keySet()) {
				Answer answer = answerMap.get(key);
				Question question = answer.getRefQuestion().get();

				out.print(as.getId());
				out.print(",");
				out.print(as.getName());
				out.print(",");
				out.print(sdf.format(as.getAnswered()));
				out.print(",");
				out.print(exam.getName());
				out.print(",");
				out.print(toi.getNo());
				out.print(",");
				out.print(toi.getRefGenre().get().getNo());
				out.print(",");
				out.print(toi.getRefGenre().get().getName());
				out.print(",");
				out.print(toi.getName());
				out.print(",");
				out.print(answer.getId());
				out.print(",");
				out.print(key);
				out.print(",");
				out.print(question.getName());
				out.print(",");
				out.print(question.getAnswers());
				out.print(",");
				out.print(answer.getAnswers());
				out.print(",");
				out.print(answer.isCorrect() ? 1 : 0);

				out.println();
			}
			out.flush();
		}

	}
}