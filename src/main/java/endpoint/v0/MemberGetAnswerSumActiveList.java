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

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class Exam
 */
@WebServlet("/endpoint/v0/member/get/AnswerSumId/List")
public final class MemberGetAnswerSumActiveList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Optional<String> optExamId = Optional.ofNullable(request.getParameter("email"));
		List<Long> toiIdList = new ArrayList<Long>();

		if (optExamId.isPresent()) {
			try {
				String email = optExamId.get();
				List<AnswerSum> toiList = new ArrayList<AnswerSum>();
				toiList =  ofy().load().type(AnswerSum.class).filter("name", email).list();
				toiIdList = toiList.stream().map(AnswerSum::getId).collect(Collectors.toList());
			} catch (NumberFormatException e) {
			}
		}
		
		Gson gson = new Gson();
		response.getWriter().println(gson.toJson(toiIdList));
	}

}
