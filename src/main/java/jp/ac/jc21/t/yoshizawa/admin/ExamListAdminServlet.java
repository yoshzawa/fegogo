package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/exam/list" })
public class ExamListAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Map<Long,Exam> examMap = Exam.loadAll();
		request.setAttribute("examMap", examMap);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/examListAdmin.jsp");
		rd.forward(request, response);

	}
}