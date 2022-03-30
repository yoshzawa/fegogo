package endpoint.v0;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Member;

/**
 * Servlet implementation class Exam
 */
@WebServlet("/endpoint/v0/member/email/list")
public final class MemberEmailList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Optional<String> optOrder = Optional.ofNullable(request.getParameter("order"));

		Stream<Member> memberStream = ofy().load().type(Member.class).list()
				.stream();
		String order = optOrder.orElse("");
		switch (order) {
		case "modified":
			memberStream = memberStream.sorted(Comparator.comparing(Member::getModified));
			break;
		case "created":
			memberStream = memberStream.sorted(Comparator.comparing(Member::getCreated));
			break;
		default:
			memberStream = memberStream.sorted(Comparator.comparing(Member::geteMail));
			break;
		}

		List<String> MemberEmailList = memberStream.map(Member::geteMail)
				.collect(Collectors.toList());

		Gson gson = new Gson();

		response.getWriter().println(gson.toJson(MemberEmailList));
	}

}
