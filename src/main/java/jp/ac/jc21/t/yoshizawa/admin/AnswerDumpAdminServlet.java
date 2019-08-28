package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		int count = 0;

		response.setContentType("text/csv; charset=Windows-31J");
		PrintWriter out = response.getWriter();

		List<AnswerSum> answerSumList = AnswerSum.loadAll();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		out.println("ñ‚id,âìöé“,âìöì˙,ééå±,ñ‚,ï™ñÏèá,ï™ñÏ,ñ‚è⁄ç◊," + "ê›ñ‚id,èoëËèá,ê›ñ‚,ê≥â,âìö,ê≥åÎ");

		Date dateStart = new Date();
		log.info(getServletName() + "[" + dateStart.toString() + "]START");
		for (AnswerSum as : answerSumList) {

			String ansSumDump = as.getAnswerSumDumpCSV();
			if (ansSumDump == null) {
				Toi toi = as.getRefToi().get();
				Exam exam = toi.getExam();
				String s = as.getId() + "," + as.getName() + "," + sdf.format(as.getAnswered()) + "," + exam.getName()
						+ "," + toi.getNo() + "," + toi.getRefGenre().get().getNo() + ","
						+ toi.getRefGenre().get().getName() + "," + toi.getName() + ",";
				as.setAnswerSumDumpCSV(s);
				ansSumDump = s;
				as.save();
			}

			Map<Integer, Answer> answerMap = as.getMapAnswer();
			for (Integer key : answerMap.keySet()) {
				Answer answer = answerMap.get(key);

				out.print(ansSumDump);

				String ansDump = answer.getAnswerDumpCSV();

				if (ansDump == null) {
					Question question = answer.getRefQuestion().get();
					String s = answer.getId() + "," + key + "," + question.getName() + "," + question.getAnswers() + ","
							+ answer.getAnswers() + "," + (answer.isCorrect() ? 1 : 0);
					ansDump = s;
					answer.setAnswerDumpCSV(s);
					answer.save();
				}

				out.print(ansDump);

				out.println();
			}
			out.flush();
			if(count++ % 5 == 0) {
				log.info(getServletName() + "[" + new Date().toString() + "]" + count);
			}
		}
		Date dateEnd = new Date();
		log.info(getServletName() + "[" + dateEnd.toString() + "]END");
		log.info(getServletName() + "[" + ((dateEnd.getTime() - dateStart.getTime())/1000) + "s]END");
		log.info(getServletName() + "[" + ((dateEnd.getTime() - dateStart.getTime())/count) + "ms/AnswerSum]END");

	}
}