package endpoint.v1;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.googlecode.objectify.Key;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class Exam
 */
@WebServlet("/endpoint/v1/exam/get")
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
		Optional<Exam> optExam;

		if (optExamId.isPresent()) {
			try {
				long examId = Long.parseLong(optExamId.get());
				Key<Exam> key = Key.create(Exam.class, examId);
				Exam exam = Exam.getById(key);
				optExam = Optional.ofNullable(exam);
			} catch (NumberFormatException e) {
				optExam = Optional.empty();
			}
		} else {
			optExam = Optional.empty();
		}
		Gson gson = new Gson();
		response.getWriter().println(gson.toJson(optExam));
	}

}
