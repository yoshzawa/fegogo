package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import jp.ac.jc21.t.yoshizawa.objectify.*;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/answer/dumpAnswer.csv" })
public class AnswerDumpAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/csv; charset=Windows-31J");
		PrintWriter out = response.getWriter();

		List<AnswerSum> answerSumList = AnswerSum.loadAll();

		UserService userService = UserServiceFactory.getUserService();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		out.println( "‰ğ“šÒ,‰ğ“š“ú,Œ±,–â,–âÚ×,o‘è‡,İ–â,³‰ğ,‰ğ“š,³Œë" );

		for (AnswerSum as : answerSumList) {
			Toi toi = as.getRefToi().get();
			Exam exam = toi.getParent();
			float point=(100.0f * as.getNoOfSeikai() / as.getNoOfAnswer());
			Map<Integer, Answer> answerMap = as.getMapAnswer();
			for (Integer i : answerMap.keySet()) {
				Answer a = answerMap.get(i);
				

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
			out.print( i  );
			out.print( "," );
			out.print( a.getRefQuestion().get().getName()  );
			out.print( "," );
			out.print( a.getRefQuestion().get().getAnswers()  );
			out.print( "," );
			out.print( a.getAnswers()  );
			out.print( "," );
			out.print( a.isCorrect()?1:0  );
			out.print( "," );

			out.println(  );
			}

		}

	}
}