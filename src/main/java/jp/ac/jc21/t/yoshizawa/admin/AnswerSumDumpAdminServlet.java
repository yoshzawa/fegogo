package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.*;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/answerSum/dumpAnswerSummary.csv" })

public class AnswerSumDumpAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(AnswerSumDumpAdminServlet.class.getName());

		response.setContentType("text/csv; charset=Windows-31J");
		PrintWriter out = response.getWriter();

		List<AnswerSum> answerSumList = AnswerSum.loadAll();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		out.println( "ID,‰ğ“šÒ,‰ğ“š“ú,Œ±,–â,•ª–ì‡,•ª–ì,–âÚ×,³‰ğ”,–â‘è”,³‰ğ—¦" );
		
		Date dateStart = new Date();
		log.info(getServletName() + "[" + dateStart.toString() + "]START");

		for (AnswerSum as : answerSumList) {
			if(as.getRefMember() != null) {

				String ansSumDump = as.getAnswerSumDumpCSV();
				if(ansSumDump == null) {
					Toi toi = as.getRefToi().get();
					Exam exam = toi.getExam();
					String s = as.getId() + "," 
							+ as.getName() + "," 
							+ sdf.format(as.getAnswered()) + "," 
							+ exam.getName() + ","
							+ toi.getNo() + "," 
							+ toi.getRefGenre().get().getNo() + "," 
							+ toi.getRefGenre().get().getName() + "," 
							+ toi.getName() + ",";
					as.setAnswerSumDumpCSV(s);
					ansSumDump = s;
					as.save();
				}
				float point=(100.0f * as.getNoOfSeikai() / as.getNoOfAnswer());
				

				out.print( ansSumDump );
				out.print( as.getNoOfSeikai()  );
				out.print( "," );
				out.print( as.getNoOfAnswer()  );
				out.print( "," );
				out.print( String.format("%1$.1f", point)  );

				out.println(  );
				out.flush();
			}
		}
		Date dateEnd = new Date();
		log.info(getServletName() + "[" + dateEnd.toString() + "]END");
		log.info(getServletName() + "[" +(dateEnd.getTime() - dateStart.getTime())+ "ms]END");
	}
}