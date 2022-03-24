package endpoint.v0;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

/**
 * Servlet implementation class Exam
 */
@WebServlet("/endpoint/v0/exam/get")
public final class ExamGet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Optional<String> optExamId = Optional.ofNullable(request.getParameter("ExamId"));
		List<Exam> examList = new ArrayList<Exam>();

		if (optExamId.isPresent()) {
			try {
				long examId = Long.parseLong(optExamId.get());
				examList = ofy().load().type(Exam.class).filter("YYYYMM", examId).list();

			} catch (NumberFormatException e) {
			}
		}
		Gson gson = new Gson();
		response.getWriter().println(gson.toJson(examList));
	}

}
