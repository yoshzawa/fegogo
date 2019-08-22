package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import jp.ac.jc21.t.yoshizawa.objectify.*;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/answerSum/dumpAnswerSummary.csv" })

public class AnswerSumDumpAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/csv; charset=Windows-31J");
		PrintWriter out = response.getWriter();

		List<AnswerSum> answerSumList = AnswerSum.loadAll();

		UserService userService = UserServiceFactory.getUserService();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		out.println( "ID,‰ğ“šÒ,‰ğ“š“ú,Œ±,–â,–âÚ×,³‰ğ”,–â‘è”,³‰ğ—¦" );

		for (AnswerSum as : answerSumList) {
			if(as.getRefMember() == null) {
				continue;
			}
			Toi toi = as.getRefToi().get();
			Exam exam = toi.getExam();
			float point=(100.0f * as.getNoOfSeikai() / as.getNoOfAnswer());
	

			out.print( as.getId() );
			out.print( "," );
			out.print( as.getName() );
			out.print( "," );
			out.print( sdf.format(as.getAnswered()) );
			out.print( "," );
			out.print( exam.getName() );
			out.print( "," );
			out.print( toi.getNo()  );
			out.print( "," );
			out.print( toi.getName()  );
			out.print( "," );
			out.print( as.getNoOfSeikai()  );
			out.print( "," );
			out.print( as.getNoOfAnswer()  );
			out.print( "," );
			out.print( String.format("%1$.1f", point)  );

			out.println(  );

		}



	}
}