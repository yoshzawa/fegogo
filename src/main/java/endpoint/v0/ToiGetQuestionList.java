package endpoint.v0;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class Exam
 */
@WebServlet("/endpoint/v0/toi/get/questionId/List")
public final class ToiGetQuestionList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Optional<String> optToiId = Optional.ofNullable(request.getParameter("ToiId"));
		List<Long> questionIdList = new ArrayList<Long>();

		if (optToiId.isPresent()) {
			try {
				long toiId = Long.parseLong(optToiId.get());
				List<Question> questionList = new ArrayList<Question>();
				questionList =  ofy().load().type(Question.class).filter("toiId", toiId).list();
				questionIdList = questionList.stream().map(Question::getId).collect(Collectors.toList());
			} catch (NumberFormatException e) {
			}
		}
		
		Gson gson = new Gson();
		response.getWriter().println(gson.toJson(questionIdList));
	}

}
