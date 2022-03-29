package endpoint.v0;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
@WebServlet("/endpoint/v0/exam/YYYYMM/list")
public final class ExamList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		List<Exam> examList = ofy().load().type(Exam.class).list();
		List<Long> examIdList = examList.stream().sorted(Comparator.comparing(Exam::getYYYYMM)).map(Exam::getYYYYMM).collect(Collectors.toList());

        Gson gson = new Gson();
        
        response.getWriter().println(gson.toJson(examIdList));
	}

}
