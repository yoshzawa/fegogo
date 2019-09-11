package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
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

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

import static java.nio.charset.StandardCharsets.UTF_8;;

/**
 * Servlet implementation class ExportTestServlet
 */
@WebServlet("/admin/makeAnswerCSV")
public class MakeAnswerCSVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MakeAnswerCSVServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageString = request.getParameter("page");
		int page=0;
		try {
		page= Integer.parseInt(pageString);
		} catch (NumberFormatException e) {}
		
		String string = getAnswerSumDump(page);
		Storage storage = StorageOptions.getDefaultInstance().getService();
		BlobId blobId = BlobId.of("fegogo.appspot.com", "dumpAnswer" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(page) + ".csv");
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/csv").build();
		Blob blob = storage.create(blobInfo, string.getBytes());
		response.getWriter().println("finished");
	}

	public String getAnswerSumDump(int page) {
		final Logger log = Logger.getLogger(MakeAnswerCSVServlet.class.getName());

		String result = "";
		result = "ñ‚id,âìöé“,âìöì˙,ééå±,ñ‚,ï™ñÏèá,ï™ñÏ,ñ‚è⁄ç◊," + "ê›ñ‚id,èoëËèá,ê›ñ‚,ê≥â,âìö,ê≥åÎ";
		result += "\n";

		List<AnswerSum> answerSumList = AnswerSum.loadAll();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		Date dateStart = new Date();
		log.info(getServletName() + "[" + dateStart.toString() + "]START");

		int count = 0;
		final boolean forceRewrite = false;
//		for (AnswerSum as : answerSumList) {
		for(int pointer=page ; pointer < answerSumList.size() ; pointer+=30) {
			AnswerSum as = answerSumList.get(pointer);
			if ((as.getRefMember() != null) 
					//&& (as.getId() % 10 == page)
					) {

				String ansSumDump = as.getAnswerSumDumpCSV();
				if ((forceRewrite == true) || (ansSumDump == null)) {
					Toi toi = as.getRefToi().get();
					Exam exam = toi.getExam();
					String ss = as.getId() + "," + as.getName() + "," + sdf.format(as.getAnswered()) + ","
							+ exam.getName() + "," + toi.getNo() + "," + toi.getRefGenre().get().getNo() + ","
							+ toi.getRefGenre().get().getName() + "," + toi.getName() + ",";
					as.setAnswerSumDumpCSV(ss);
					ansSumDump = ss;
					as.save();
				}
				Map<Integer, Answer> answerMap = as.getMapAnswer();
				for (Integer key : answerMap.keySet()) {
					Answer answer = answerMap.get(key);

					String ansDump = answer.getAnswerDumpCSV();

					if ((forceRewrite == true) || (ansDump == null)) {
						Question question = answer.getRefQuestion().get();
						String s = answer.getId() + "," + key + "," + question.getName() + "," + question.getAnswers()
								+ "," + answer.getAnswers() + "," + (answer.isCorrect() ? 1 : 0);
						ansDump = s;
						answer.setAnswerDumpCSV(s);
						answer.save();
					}

					result += ansSumDump;
					result += ansDump;
					result += "\n";

				}
			}
			count++;
			if (count % 10 == 0) {
				log.info("[page" +page+":"+ new Date().toString() + "]" + count);
			}
		}
		Date dateEnd = new Date();
		log.info(getServletName() + "[page" +page+":"+ dateEnd.toString() + "]END");
		log.info(getServletName() + "[page" +page+":"+(dateEnd.getTime() - dateStart.getTime()) + "ms]END");

		return result;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
