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
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class Exam
 */
@WebServlet("/endpoint/v0/toi/get")
public final class ToiGet extends HttpServlet {
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
		List<Toi> toiList = new ArrayList<Toi>();

		if (optToiId.isPresent()) {
			try {
				long toiId = Long.parseLong(optToiId.get());
				Optional<Toi> toi = Optional.ofNullable(ofy().load().type(Toi.class).id(toiId).now());
				toi.ifPresent(toiList::add);

			} catch (NumberFormatException e) {
			}
		}
		Gson gson = new Gson();
		response.getWriter().println(gson.toJson(toiList));
	}

}
