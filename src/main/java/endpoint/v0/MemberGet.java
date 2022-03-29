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

import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class Exam
 */
@WebServlet("/endpoint/v0/member/get")
public final class MemberGet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Optional<String> optEmail = Optional.ofNullable(request.getParameter("email"));
		List<Member> memberList = new ArrayList<Member>();

		if (optEmail.isPresent()) {
			try {
				String memberId = optEmail.get();
				Optional<Member> member = Optional.ofNullable(ofy().load().type(Member.class).id(memberId).now());
				member.ifPresent(memberList::add);

			} catch (NumberFormatException e) {
			}
		}
		Gson gson = new Gson();
		response.getWriter().println(gson.toJson(memberList));
	}

}
