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

		out.println("ñ‚id,âìöé“,âìöì˙,ééå±,ñ‚,ñ‚è⁄ç◊,ê›ñ‚id,èoëËèá,ê›ñ‚,ê≥â,âìö,ê≥åÎ");

		for (AnswerSum as : answerSumList) {
			Toi toi = as.getRefToi().get();
			Exam exam = toi.getExam();
			//log.info("AnswerSum="+as.getId().toString());
			float point = (100.0f * as.getNoOfSeikai() / as.getNoOfAnswer());
			Map<Integer, Answer> answerMap = as.getMapAnswer();
			for (Integer i : answerMap.keySet()) {
				Answer a = answerMap.get(i);
				//log.info("Answer="+a.getId().toString());

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
				out.print(toi.getName());
				out.print(",");
				out.print(a.getId());
				out.print(",");
				out.print(i);
				out.print(",");
				out.print(a.getRefQuestion().get().getName());
				out.print(",");
				out.print(a.getRefQuestion().get().getAnswers());
				out.print(",");
				out.print(a.getAnswers());
				out.print(",");
				out.print(a.isCorrect() ? 1 : 0);

				out.println();
			}
			out.flush();
		}

	}
}