package endpoint.v1;

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

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import java.util.logging.Level;

/**
 * Servlet implementation class Exam
 */
@WebServlet("/endpoint/v1/exam/get")
public final class ExamGet extends HttpServlet implements ExamFunction {
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

		String s;
		if (optExamId.isPresent()) {
			s = ExamFunction.getExamByExamIdWithCache(optExamId);
		} else {
			s = "[]";
		}

		response.getWriter().println(s);
	}

}
