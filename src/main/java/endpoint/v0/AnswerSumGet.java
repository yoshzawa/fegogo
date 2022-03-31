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

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class Exam
 */
@WebServlet("/endpoint/v0/answerSum/get")
public final class AnswerSumGet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Optional<String> optToiId = Optional.ofNullable(request.getParameter("AnswerSumId"));
		List<AnswerSum> toiList = new ArrayList<AnswerSum>();

		if (optToiId.isPresent()) {
			try {
				long toiId = Long.parseLong(optToiId.get());
				Optional<AnswerSum> toi = Optional.ofNullable(ofy().load().type(AnswerSum.class).id(toiId).now());
				toi.ifPresent(toiList::add);

			} catch (NumberFormatException e) {
			}
		}
		Gson gson = new Gson();
		response.getWriter().println(gson.toJson(toiList));
	}

}
